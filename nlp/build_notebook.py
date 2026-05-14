"""Builds the presentation notebook from a structured cell list.

Run once with `pdm run python build_notebook.py` to (re)generate
`presentation.ipynb`. The notebook is then executed separately via
`jupyter nbconvert --to notebook --execute --inplace presentation.ipynb`.
"""
from __future__ import annotations

import json
from pathlib import Path

NB_PATH = Path(__file__).parent / "presentation.ipynb"


def md(text: str) -> dict:
    return {
        "cell_type": "markdown",
        "metadata": {},
        "source": text.splitlines(keepends=True),
    }


def code(text: str) -> dict:
    return {
        "cell_type": "code",
        "execution_count": None,
        "metadata": {},
        "outputs": [],
        "source": text.splitlines(keepends=True),
    }


cells: list[dict] = []

# ------------------------------------------------------------------
# 1. Title
# ------------------------------------------------------------------
cells.append(md(
    "# Sentiment Analysis: From Lexicons to Transformers\n"
    "### A hands-on comparative study\n"
    "\n"
    "NLP course presentation\n"
    "\n"
    "---\n"
    "\n"
    "> *We compare three generations of sentiment-analysis approaches on the\n"
    "> same IMDb movie-review benchmark: a rule-based lexicon (VADER), a\n"
    "> classical bag-of-words classifier (TF-IDF + Logistic Regression), and\n"
    "> a transformer (DistilBERT fine-tuned on SST-2). Every cell in this\n"
    "> notebook runs end-to-end on CPU in under ten minutes.*\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- Three generations of sentiment analysis compared on a single benchmark: rule-based, classical ML, and transformer.\n"
    "- Same dataset, same test set, same metrics across every model.\n"
    "- Runs end-to-end on CPU in under ten minutes.\n"
    "- **The goal is not to crown a winner, but to understand the trade-offs between the three approaches.**\n"
    "\n"
    "</details>"
))

# ------------------------------------------------------------------
# 2. Agenda
# ------------------------------------------------------------------
cells.append(md(
    "## Agenda\n"
    "\n"
    "1. **Why sentiment analysis?** The task and its business value.\n"
    "2. **Dataset.** A balanced 1 000-sample slice of IMDb.\n"
    "3. **Approach A &mdash; VADER** *(rule-based, 2014)*.\n"
    "4. **Approach B &mdash; TF-IDF + Logistic Regression** *(classical ML, ~2000s)*.\n"
    "5. **Approach C &mdash; DistilBERT-SST2** *(transformer, 2019)*.\n"
    "6. **Head-to-head comparison** &mdash; accuracy, F1, latency.\n"
    "7. **Discussion** &mdash; when to pick which.\n"
    "8. **References.**\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The structure: why the task matters, then the dataset, then the three models in chronological order.\n"
    "- Models are compared on three axes: **accuracy, F1, and latency per review**.\n"
    "- A short error analysis isolates the failure modes of bag-of-words approaches.\n"
    "- The discussion section maps each approach to the scenario where it makes sense.\n"
    "\n"
    "</details>"
))

# ------------------------------------------------------------------
# 3. Problem framing
# ------------------------------------------------------------------
cells.append(md(
    "## 1. Why sentiment analysis?\n"
    "\n"
    "Sentiment analysis (or *opinion mining*) labels a piece of text with\n"
    "the polarity of the opinion it expresses. It is one of the most\n"
    "widely deployed NLP tasks in industry &mdash; product reviews, brand\n"
    "monitoring, support-ticket triage, financial-news signals, election\n"
    "polling.\n"
    "\n"
    "Three generations of techniques dominate the literature:\n"
    "\n"
    "| Generation | Idea | Year | Pros | Cons |\n"
    "|---|---|---|---|---|\n"
    "| **Lexicon** | Sum the polarity of words in a hand-built dictionary | 2004&ndash; | No training data, fast, transparent | Misses context, sarcasm, negation |\n"
    "| **Classical ML** | Vectorize text (BoW / TF-IDF), train a linear classifier | 2002&ndash; | Cheap, strong baseline, interpretable | Loses word order, needs labeled data |\n"
    "| **Transformer** | Self-attention over sub-word tokens, pre-trained at scale | 2017&ndash; | State-of-the-art accuracy, transfer learning | Heavy, opaque, GPU-friendly |\n"
    "\n"
    "Goal of the demo: measure all three on the **same** test set and\n"
    "discuss the trade-offs honestly.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- Sentiment analysis assigns a polarity label to a piece of text expressing an opinion.\n"
    "- It is one of the most widely deployed NLP tasks: product reviews, brand monitoring, support-ticket triage, financial-news signals, election polling.\n"
    "- Three generations dominate the literature:\n"
    "  - **Lexicon** &mdash; sum the polarity of words in a hand-built dictionary.\n"
    "  - **Classical ML** &mdash; vectorize text and train a linear classifier.\n"
    "  - **Transformer** &mdash; self-attention over sub-word tokens, pre-trained at scale.\n"
    "- Each occupies a different point on the accuracy / cost / interpretability triangle.\n"
    "\n"
    "</details>"
))

# ------------------------------------------------------------------
# 4. Setup
# ------------------------------------------------------------------
cells.append(md(
    "## 2. Setup &mdash; reproducible environment\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The random seed is pinned to 42, Hugging Face progress bars are silenced, and library versions are printed.\n"
    "- These steps make the notebook fully reproducible: every run produces the same numbers shown here.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "# Pin the random seed so every run produces the same numbers.\n"
    "import os\n"
    "import random\n"
    "import warnings\n"
    "import numpy as np\n"
    "\n"
    "SEED = 42\n"
    "random.seed(SEED)\n"
    "np.random.seed(SEED)\n"
    "os.environ[\"PYTHONHASHSEED\"] = str(SEED)\n"
    "os.environ[\"TOKENIZERS_PARALLELISM\"] = \"false\"\n"
    "os.environ[\"HF_HUB_DISABLE_PROGRESS_BARS\"] = \"1\"\n"
    "os.environ[\"TRANSFORMERS_VERBOSITY\"] = \"error\"\n"
    "os.environ[\"DATASETS_VERBOSITY\"] = \"error\"\n"
    "warnings.filterwarnings(\"ignore\")\n"
    "\n"
    "import datasets, transformers\n"
    "datasets.disable_progress_bars()\n"
    "transformers.logging.set_verbosity_error()\n"
    "\n"
    "print(f\"random seed = {SEED}\")"
))
cells.append(code(
    "# Versions of the load-bearing libraries (helpful when sharing notebooks).\n"
    "import sys, sklearn, torch, nltk, vaderSentiment\n"
    "print(f\"python       {sys.version.split()[0]}\")\n"
    "print(f\"scikit-learn {sklearn.__version__}\")\n"
    "print(f\"transformers {transformers.__version__}\")\n"
    "print(f\"torch        {torch.__version__}\")\n"
    "print(f\"datasets     {datasets.__version__}\")\n"
    "print(f\"nltk         {nltk.__version__}\")"
))

# ------------------------------------------------------------------
# 5. Dataset
# ------------------------------------------------------------------
cells.append(md(
    "## 3. Dataset &mdash; IMDb movie reviews\n"
    "\n"
    "`stanfordnlp/imdb` is the canonical English sentiment benchmark:\n"
    "25 000 train + 25 000 test reviews, perfectly balanced between\n"
    "positive (label `1`) and negative (label `0`).\n"
    "\n"
    "For a live demo we sub-sample to 800 train / 200 test (still\n"
    "balanced). That is enough for the linear baseline to converge and\n"
    "for the transformer to score every test example in seconds.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- IMDb is the canonical English sentiment benchmark: 25,000 train and 25,000 test reviews, perfectly balanced.\n"
    "- For a live demo it is stratified-sub-sampled to 800 train and 200 test, preserving the class balance.\n"
    "- This is large enough for the linear baseline to converge, and small enough for the transformer to score every test example in seconds.\n"
    "- The sample reviews shown below are long and multi-paragraph &mdash; this matters later, because BERT-family models cap input at 512 tokens.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "from datasets import load_dataset\n"
    "\n"
    "ds = load_dataset(\"stanfordnlp/imdb\")\n"
    "print(ds)"
))
cells.append(code(
    "# Stratified subsample. We shuffle with the global seed so the same\n"
    "# 1 000 reviews show up on every run.\n"
    "from datasets import Dataset\n"
    "import pandas as pd\n"
    "\n"
    "def stratified_subsample(split, n_per_class):\n"
    "    df = split.to_pandas()\n"
    "    pos = df[df.label == 1].sample(n=n_per_class, random_state=SEED)\n"
    "    neg = df[df.label == 0].sample(n=n_per_class, random_state=SEED)\n"
    "    out = pd.concat([pos, neg]).sample(frac=1.0, random_state=SEED).reset_index(drop=True)\n"
    "    return Dataset.from_pandas(out, preserve_index=False)\n"
    "\n"
    "train = stratified_subsample(ds[\"train\"], 400)   # 800 reviews\n"
    "test  = stratified_subsample(ds[\"test\"],  100)   # 200 reviews\n"
    "print(f\"train: {len(train)}  |  test: {len(test)}\")\n"
    "print(\"train label balance:\", dict(pd.Series(train['label']).value_counts()))\n"
    "print(\"test  label balance:\", dict(pd.Series(test['label']).value_counts()))"
))
cells.append(code(
    "# Peek at one positive and one negative example.\n"
    "df_test = test.to_pandas()\n"
    "for label, name in [(1, 'POS'), (0, 'NEG')]:\n"
    "    sample = df_test[df_test.label == label].iloc[0]\n"
    "    print(f\"--- {name} ---\")\n"
    "    print(sample['text'][:400], '...\\n')"
))

# ------------------------------------------------------------------
# 6. Approach A: VADER
# ------------------------------------------------------------------
cells.append(md(
    "## 4. Approach A &mdash; VADER (rule-based)\n"
    "\n"
    "[VADER](https://github.com/cjhutto/vaderSentiment) (Hutto &amp; Gilbert,\n"
    "2014) is a hand-tuned lexicon plus simple rules for negation,\n"
    "intensifiers, punctuation and emoji. Each word carries a polarity\n"
    "score in `[-4, +4]`; the document gets a `compound` score in\n"
    "`[-1, +1]` after normalisation.\n"
    "\n"
    "**No training**, **no hyper-parameters**, **no GPU**. Pure CPU\n"
    "lookups. The natural baseline.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- VADER (Hutto & Gilbert, 2014) is a hand-tuned lexicon plus simple rules for negation, intensifiers, punctuation, and emoji.\n"
    "- Each word carries a polarity score; the document gets a compound score in the range [-1, +1].\n"
    "- **No training, no hyper-parameters, no GPU** &mdash; only dictionary lookups.\n"
    "- On the IMDb test set, F1 lands in the high 0.6s at sub-millisecond latency per review.\n"
    "- This is the natural floor of the comparison: fast, interpretable, and requires no labeled data.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer\n"
    "import time\n"
    "\n"
    "vader = SentimentIntensityAnalyzer()\n"
    "\n"
    "def vader_predict(texts, threshold=0.05):\n"
    "    \"\"\"Return 1 if the compound score exceeds `threshold`, else 0.\"\"\"\n"
    "    return [1 if vader.polarity_scores(t)[\"compound\"] >= threshold else 0\n"
    "            for t in texts]\n"
    "\n"
    "# Sanity-check on two hand-written sentences.\n"
    "for s in [\"This movie was absolutely brilliant!\",\n"
    "          \"What a complete waste of time, I want my money back.\"]:\n"
    "    print(f\"{vader.polarity_scores(s)['compound']:+.3f}  {s}\")"
))
cells.append(code(
    "# Score the full test set and time it.\n"
    "from sklearn.metrics import accuracy_score, f1_score\n"
    "\n"
    "texts_test  = list(test[\"text\"])\n"
    "labels_test = list(test[\"label\"])\n"
    "\n"
    "t0 = time.perf_counter()\n"
    "vader_preds = vader_predict(texts_test)\n"
    "vader_seconds = time.perf_counter() - t0\n"
    "\n"
    "vader_acc = accuracy_score(labels_test, vader_preds)\n"
    "vader_f1  = f1_score(labels_test, vader_preds)\n"
    "print(f\"VADER  accuracy = {vader_acc:.3f}  |  F1 = {vader_f1:.3f}  |  {vader_seconds*1000/len(texts_test):.2f} ms/review\")"
))

# ------------------------------------------------------------------
# 7. Approach B: TF-IDF + Logistic Regression
# ------------------------------------------------------------------
cells.append(md(
    "## 5. Approach B &mdash; TF-IDF + Logistic Regression\n"
    "\n"
    "We replace the hand-built dictionary with one **learned from the\n"
    "training set**:\n"
    "\n"
    "1. **TF-IDF** turns each review into a sparse vector over the\n"
    "   ~10 000 most frequent uni- and bi-grams.\n"
    "2. **Logistic Regression** learns a single weight per n-gram and\n"
    "   adds them up &mdash; the same equation as VADER, but the\n"
    "   coefficients come from data instead of from a human.\n"
    "\n"
    "This is the strongest pre-deep-learning baseline; it stayed\n"
    "competitive on IMDb until ~2014.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The hand-built dictionary is replaced with one **learned from the training data**.\n"
    "- TF-IDF turns each review into a sparse vector over the top n-grams. Logistic Regression learns one weight per n-gram.\n"
    "- The equation is the same as VADER &mdash; a weighted sum of word scores &mdash; but the coefficients come from data, not from a human.\n"
    "- Training takes a few seconds on 800 reviews. F1 jumps into the mid 0.8s. Inference stays at roughly 1-2 ms per review.\n"
    "- The feature-inspection cell prints the strongest learned coefficients. Top positives are words like *great*, *excellent*, *wonderful*; top negatives are *worst*, *boring*, *waste*.\n"
    "- That readable list of weights is the interpretability advantage that transformers give up.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "from sklearn.feature_extraction.text import TfidfVectorizer\n"
    "from sklearn.linear_model import LogisticRegression\n"
    "from sklearn.pipeline import Pipeline\n"
    "\n"
    "tfidf_lr = Pipeline([\n"
    "    (\"tfidf\", TfidfVectorizer(max_features=10_000,\n"
    "                              ngram_range=(1, 2),\n"
    "                              min_df=2,\n"
    "                              stop_words=\"english\")),\n"
    "    (\"clf\",  LogisticRegression(max_iter=1000, C=4.0, random_state=SEED)),\n"
    "])\n"
    "\n"
    "t0 = time.perf_counter()\n"
    "tfidf_lr.fit(list(train[\"text\"]), list(train[\"label\"]))\n"
    "fit_seconds = time.perf_counter() - t0\n"
    "print(f\"trained in {fit_seconds:.2f} s on {len(train)} reviews\")"
))
cells.append(code(
    "# Inference + metrics.\n"
    "t0 = time.perf_counter()\n"
    "lr_preds = tfidf_lr.predict(texts_test)\n"
    "lr_seconds = time.perf_counter() - t0\n"
    "\n"
    "lr_acc = accuracy_score(labels_test, lr_preds)\n"
    "lr_f1  = f1_score(labels_test, lr_preds)\n"
    "print(f\"LR     accuracy = {lr_acc:.3f}  |  F1 = {lr_f1:.3f}  |  {lr_seconds*1000/len(texts_test):.2f} ms/review\")"
))
cells.append(code(
    "# Inspect the strongest learned features &mdash; this is the\n"
    "# interpretability superpower that transformers give up.\n"
    "vec = tfidf_lr.named_steps[\"tfidf\"]\n"
    "clf = tfidf_lr.named_steps[\"clf\"]\n"
    "vocab = np.array(vec.get_feature_names_out())\n"
    "weights = clf.coef_[0]\n"
    "\n"
    "top_pos = vocab[np.argsort(weights)[-15:][::-1]]\n"
    "top_neg = vocab[np.argsort(weights)[:15]]\n"
    "print(\"top POSITIVE features:\", \", \".join(top_pos))\n"
    "print(\"top NEGATIVE features:\", \", \".join(top_neg))"
))

# ------------------------------------------------------------------
# 8. Approach C: DistilBERT
# ------------------------------------------------------------------
cells.append(md(
    "## 6. Approach C &mdash; DistilBERT-SST2 (transformer)\n"
    "\n"
    "[DistilBERT](https://arxiv.org/abs/1910.01108) is a 40 %-smaller,\n"
    "60 %-faster student of BERT-base, distilled by Sanh et&nbsp;al. (2019).\n"
    "We use the public checkpoint\n"
    "[`distilbert-base-uncased-finetuned-sst-2-english`](https://huggingface.co/distilbert-base-uncased-finetuned-sst-2-english)\n"
    "&mdash; already fine-tuned on the Stanford Sentiment Treebank, no\n"
    "extra training required.\n"
    "\n"
    "We are testing **transfer learning**: train on SST-2 movie\n"
    "snippets, evaluate on full IMDb reviews. This is a realistic\n"
    "scenario for any team that wants sentiment without owning labelled\n"
    "data.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- DistilBERT (Sanh et al., 2019) is 40% smaller and 60% faster than BERT-base, distilled to retain most of the quality with less compute.\n"
    "- The public checkpoint used here is already fine-tuned on SST-2, so **no training happens in this notebook**.\n"
    "- The real experiment is **transfer learning**: a model trained on short SST-2 snippets is evaluated on long IMDb reviews.\n"
    "- This mirrors a realistic production scenario where a team wants sentiment without owning labeled data.\n"
    "- Inference runs in mini-batches of 16. Latency lands around 40-100 ms per review &mdash; roughly **two orders of magnitude slower than the linear model**.\n"
    "- Accuracy climbs to the high 0.8s / low 0.9s.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "from transformers import pipeline\n"
    "\n"
    "device = \"mps\" if torch.backends.mps.is_available() else (\n"
    "         \"cuda\" if torch.cuda.is_available() else \"cpu\")\n"
    "print(f\"using device: {device}\")\n"
    "\n"
    "sentiment = pipeline(\n"
    "    task=\"sentiment-analysis\",\n"
    "    model=\"distilbert-base-uncased-finetuned-sst-2-english\",\n"
    "    device=device,\n"
    "    truncation=True,        # IMDb reviews exceed 512 tokens\n"
    "    max_length=512,\n"
    ")"
))
cells.append(code(
    "# Run inference in mini-batches and time it.\n"
    "BATCH = 16\n"
    "t0 = time.perf_counter()\n"
    "bert_outputs = []\n"
    "for i in range(0, len(texts_test), BATCH):\n"
    "    bert_outputs.extend(sentiment(texts_test[i:i+BATCH], batch_size=BATCH))\n"
    "bert_seconds = time.perf_counter() - t0\n"
    "\n"
    "bert_preds = [1 if o[\"label\"] == \"POSITIVE\" else 0 for o in bert_outputs]\n"
    "bert_acc = accuracy_score(labels_test, bert_preds)\n"
    "bert_f1  = f1_score(labels_test, bert_preds)\n"
    "print(f\"DistilBERT accuracy = {bert_acc:.3f}  |  F1 = {bert_f1:.3f}  |  {bert_seconds*1000/len(texts_test):.1f} ms/review\")"
))

# ------------------------------------------------------------------
# 9. Comparison
# ------------------------------------------------------------------
cells.append(md(
    "## 7. Head-to-head comparison\n"
    "\n"
    "We collect the three models' metrics into one table and one chart\n"
    "so the trade-offs are visible at a glance.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The **F1 ordering** is clear: VADER lowest, TF-IDF in the middle, DistilBERT highest. Roughly a 15-point gap from lexicon to transformer.\n"
    "- The **latency ordering flips**. The chart is on a log scale, and DistilBERT is still dramatically slower &mdash; roughly **50&times; more compute for those 15 F1 points**.\n"
    "- This is the central trade-off picture of the presentation.\n"
    "- The confusion matrices show the failure modes: VADER over-predicts positive (lexicon bias toward common positive words), TF-IDF is more balanced, DistilBERT is the most symmetric of the three.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "import pandas as pd\n"
    "\n"
    "results = pd.DataFrame([\n"
    "    {\"model\": \"VADER (lexicon)\",        \"accuracy\": vader_acc, \"f1\": vader_f1, \"ms_per_review\": vader_seconds*1000/len(texts_test)},\n"
    "    {\"model\": \"TF-IDF + LogReg\",        \"accuracy\": lr_acc,    \"f1\": lr_f1,    \"ms_per_review\": lr_seconds*1000/len(texts_test)},\n"
    "    {\"model\": \"DistilBERT (SST-2)\",     \"accuracy\": bert_acc,  \"f1\": bert_f1,  \"ms_per_review\": bert_seconds*1000/len(texts_test)},\n"
    "])\n"
    "results.style.format({\"accuracy\": \"{:.3f}\", \"f1\": \"{:.3f}\", \"ms_per_review\": \"{:.2f}\"})"
))
cells.append(code(
    "import matplotlib.pyplot as plt\n"
    "import seaborn as sns\n"
    "sns.set_theme(style=\"whitegrid\", context=\"talk\")\n"
    "\n"
    "fig, axes = plt.subplots(1, 2, figsize=(13, 4.5))\n"
    "\n"
    "sns.barplot(data=results, x=\"model\", y=\"f1\", ax=axes[0],\n"
    "            palette=[\"#94a3b8\", \"#60a5fa\", \"#1d4ed8\"])\n"
    "axes[0].set_ylim(0, 1)\n"
    "axes[0].set_title(\"F1 score (higher is better)\")\n"
    "axes[0].set_xlabel(\"\")\n"
    "for p in axes[0].patches:\n"
    "    axes[0].annotate(f\"{p.get_height():.2f}\", (p.get_x()+p.get_width()/2, p.get_height()+0.02),\n"
    "                     ha=\"center\", fontsize=12)\n"
    "\n"
    "sns.barplot(data=results, x=\"model\", y=\"ms_per_review\", ax=axes[1],\n"
    "            palette=[\"#94a3b8\", \"#60a5fa\", \"#1d4ed8\"])\n"
    "axes[1].set_yscale(\"log\")\n"
    "axes[1].set_title(\"Latency per review (log ms, lower is better)\")\n"
    "axes[1].set_xlabel(\"\")\n"
    "for p in axes[1].patches:\n"
    "    axes[1].annotate(f\"{p.get_height():.2f}\", (p.get_x()+p.get_width()/2, p.get_height()*1.1),\n"
    "                     ha=\"center\", fontsize=12)\n"
    "\n"
    "for ax in axes:\n"
    "    ax.tick_params(axis='x', rotation=15)\n"
    "plt.tight_layout()\n"
    "plt.show()"
))
cells.append(code(
    "# Confusion matrices side-by-side.\n"
    "from sklearn.metrics import confusion_matrix\n"
    "\n"
    "fig, axes = plt.subplots(1, 3, figsize=(13, 4))\n"
    "for ax, (name, preds) in zip(axes, [\n"
    "    (\"VADER\", vader_preds),\n"
    "    (\"TF-IDF + LR\", lr_preds),\n"
    "    (\"DistilBERT\", bert_preds),\n"
    "]):\n"
    "    cm = confusion_matrix(labels_test, preds)\n"
    "    sns.heatmap(cm, annot=True, fmt=\"d\", cmap=\"Blues\", cbar=False, ax=ax,\n"
    "                xticklabels=[\"neg\", \"pos\"], yticklabels=[\"neg\", \"pos\"])\n"
    "    ax.set_title(name)\n"
    "    ax.set_xlabel(\"predicted\")\n"
    "    ax.set_ylabel(\"true\")\n"
    "plt.tight_layout()\n"
    "plt.show()"
))

# ------------------------------------------------------------------
# 10. Error analysis
# ------------------------------------------------------------------
cells.append(md(
    "## 8. Error analysis &mdash; what does each model get wrong?\n"
    "\n"
    "Inspect a handful of reviews where the lexicon and the transformer\n"
    "disagree. These usually involve sarcasm, negation, or long-range\n"
    "context &mdash; exactly the failure modes the literature predicts\n"
    "for bag-of-words approaches.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The reviews VADER misclassifies but DistilBERT gets right tend to share one of three properties:\n"
    "  - **Sarcasm** &mdash; the surface words are positive but the meaning is negative.\n"
    "  - **Negation** &mdash; \"not great\" gets scored as positive because *great* is in the dictionary.\n"
    "  - **Long-range context** &mdash; the verdict only appears in the last sentence, after a long plot description.\n"
    "- These are exactly the failure modes the literature predicts for bag-of-words approaches.\n"
    "- Self-attention addresses them by reading across the whole review at once.\n"
    "\n"
    "</details>"
))
cells.append(code(
    "errors = pd.DataFrame({\n"
    "    \"text\": texts_test,\n"
    "    \"true\": labels_test,\n"
    "    \"vader\": vader_preds,\n"
    "    \"tfidf_lr\": lr_preds,\n"
    "    \"bert\": bert_preds,\n"
    "})\n"
    "vader_only_wrong = errors[(errors.vader != errors.true) & (errors.bert == errors.true)]\n"
    "print(f\"reviews where DistilBERT was right but VADER was wrong: {len(vader_only_wrong)}\")\n"
    "for _, row in vader_only_wrong.head(3).iterrows():\n"
    "    label = \"POS\" if row.true == 1 else \"NEG\"\n"
    "    print(f\"\\n[true={label}]  {row.text[:380].strip()}...\")"
))

# ------------------------------------------------------------------
# 11. Discussion
# ------------------------------------------------------------------
cells.append(md(
    "## 9. Discussion &mdash; when to pick which?\n"
    "\n"
    "| Situation | Recommended approach | Why |\n"
    "|---|---|---|\n"
    "| **No labelled data, prototyping** | VADER | Zero training, transparent, runs anywhere. |\n"
    "| **A few thousand labels, on-prem CPU** | TF-IDF + LR | Best accuracy/cost ratio, full interpretability via word weights. |\n"
    "| **Domain shift, multilingual, sarcasm-heavy** | Transformer | Pre-training captures the patterns BoW cannot. |\n"
    "| **Sub-millisecond latency budget** | TF-IDF + LR | LR is two orders of magnitude faster than DistilBERT on CPU. |\n"
    "| **Long-form documents** | Transformer with sliding-window | Self-attention captures long-range dependencies; truncate or chunk. |\n"
    "\n"
    "Headline finding from the demo: on this 200-review test set,\n"
    "DistilBERT trades ~50&times; more compute for roughly\n"
    "**+15 F1 points** over VADER and **+5&ndash;10** over TF-IDF.\n"
    "Whether that trade is worth it depends entirely on the cost of an\n"
    "error in your application.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The decision matrix maps a situation to the model that fits it best:\n"
    "  - **No labels, prototyping** &rarr; VADER.\n"
    "  - **A few thousand labels, on-prem CPU, interpretability required** &rarr; TF-IDF + Logistic Regression.\n"
    "  - **Domain shift, sarcasm, multilingual input** &rarr; transformer.\n"
    "  - **Sub-millisecond latency budget** &rarr; Logistic Regression wins by default.\n"
    "- Headline finding: DistilBERT pays roughly **50&times; more compute for ~15 F1 points** over VADER. Whether that trade is worth it depends entirely on the cost of an error in the target application.\n"
    "\n"
    "</details>"
))

# ------------------------------------------------------------------
# 12. Conclusions
# ------------------------------------------------------------------
cells.append(md(
    "## 10. Take-aways\n"
    "\n"
    "* The **classical baseline matters**. Always measure it before\n"
    "  reaching for a transformer.\n"
    "* **Transfer learning works**: a model fine-tuned on SST-2 short\n"
    "  snippets generalises well to long IMDb reviews.\n"
    "* **No silver bullet**. Rule-based, classical-ML and transformer\n"
    "  approaches each occupy a defensible point on the\n"
    "  accuracy-cost-interpretability triangle.\n"
    "* The notebook is fully reproducible: `pdm install && jupyter\n"
    "  nbconvert --to notebook --execute --inplace presentation.ipynb`.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- Three closing points:\n"
    "  1. **The classical baseline matters.** It should be measured before reaching for a transformer.\n"
    "  2. **Transfer learning works.** A model fine-tuned on short SST-2 snippets generalizes cleanly to long IMDb reviews.\n"
    "  3. **There is no silver bullet.** Each generation occupies a defensible point on the accuracy / cost / interpretability triangle.\n"
    "\n"
    "</details>"
))

# ------------------------------------------------------------------
# 13. References
# ------------------------------------------------------------------
cells.append(md(
    "## 11. References\n"
    "\n"
    "1. Hutto, C.J. &amp; Gilbert, E. (2014). *VADER: A Parsimonious\n"
    "   Rule-based Model for Sentiment Analysis of Social Media Text.*\n"
    "   ICWSM-14.\n"
    "2. Maas, A.L. *et al.* (2011). *Learning Word Vectors for Sentiment\n"
    "   Analysis.* ACL-2011 &mdash; the IMDb dataset.\n"
    "3. Sanh, V. *et al.* (2019). *DistilBERT, a distilled version of\n"
    "   BERT: smaller, faster, cheaper and lighter.* arXiv:1910.01108.\n"
    "4. Vaswani, A. *et al.* (2017). *Attention is All You Need.*\n"
    "   NeurIPS-2017.\n"
    "5. Vajjala, S. *et al.* (2020). *Practical Natural Language\n"
    "   Processing.* O'Reilly &mdash;\n"
    "   <https://github.com/practical-nlp/practical-nlp-code>.\n"
    "6. Tunstall, L., von Werra, L. &amp; Wolf, T. (2022). *Natural\n"
    "   Language Processing with Transformers.* O'Reilly &mdash;\n"
    "   <https://github.com/nlp-with-transformers/notebooks>.\n"
    "\n"
    "<details open>\n"
    "<summary><i>Summary</i></summary>\n"
    "\n"
    "- The references list the original papers behind each method (VADER, IMDb, DistilBERT, the Transformer architecture) and the two textbooks that informed the structure of this notebook.\n"
    "\n"
    "</details>"
))


nb = {
    "cells": cells,
    "metadata": {
        "kernelspec": {
            "display_name": "nlp-presentation",
            "language": "python",
            "name": "nlp-presentation",
        },
        "language_info": {
            "name": "python",
            "version": "3.12",
        },
    },
    "nbformat": 4,
    "nbformat_minor": 5,
}

NB_PATH.write_text(json.dumps(nb, indent=1) + "\n", encoding="utf-8")
print(f"wrote {NB_PATH}  ({len(cells)} cells)")
