const mongoose = require("mongoose");

const whitelistSchema = new mongoose.Schema(
  {
    address: {
      type: String,
      required: true,
      unique: true,
    },
    amount: {
      type: Number,
      required: true,
    },
  },
  { timestamps: true }
);

const Whitelist = mongoose.model("Whitelist", whitelistSchema);

module.exports = Whitelist;
