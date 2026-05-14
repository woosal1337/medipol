# Sentiment Analysis: From Lexicons to Transformers

NLP course presentation.

A hands-on comparative study of three generations of sentiment analysis on
the IMDb movie-review benchmark:

1. **VADER** &mdash; a rule-based lexicon (Hutto &amp; Gilbert, 2014).
2. **TF-IDF + Logistic Regression** &mdash; the strongest classical-ML
   baseline.
3. **DistilBERT-SST2** &mdash; a transformer pre-trained on Stanford
   Sentiment Treebank (Sanh *et al.*, 2019).

The whole notebook runs end-to-end on CPU in well under ten minutes.

## Files

| Path | What it is |
|---|---|
| `presentation.ipynb` | The presentation. Run top-to-bottom, every cell has visible output. |
| `build_notebook.py` | Regenerates the notebook from a Python source of truth (so the slides are diff-friendly). |
| `pyproject.toml` | PDM-managed dependency manifest. Pinned to Python 3.12. |
| `pdm.lock` | Reproducible dependency lock. |

## Running it

The project is managed with [PDM](https://pdm-project.org). The virtualenv
lives in `.venv/` next to `pyproject.toml`.

```bash
# 1. Install dependencies into a project-local .venv
pdm install

# 2. Register the venv as a Jupyter kernel called "nlp-presentation"
pdm run python -m ipykernel install --user --name nlp-presentation \
    --display-name "nlp-presentation"

# 3a. Open the notebook interactively
pdm run jupyter lab presentation.ipynb

# 3b. ...or re-execute it headlessly
pdm run jupyter nbconvert --to notebook --execute --inplace presentation.ipynb \
    --ExecutePreprocessor.timeout=900
```

## Regenerating the notebook

Edit `build_notebook.py` and run:

```bash
pdm run python build_notebook.py
```

This rewrites `presentation.ipynb` with empty outputs. Re-execute it to
get the cell outputs back.

## References

* Practical NLP &mdash; <https://github.com/practical-nlp/practical-nlp-code>
* NLP with Transformers &mdash; <https://github.com/nlp-with-transformers/notebooks>
