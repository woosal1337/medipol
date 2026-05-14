const mongoose = require("mongoose");

const blacklistSchema = new mongoose.Schema(
  {
    address: {
      type: String,
      required: true,
      unique: true,
    },
  },
  { timestamps: true }
);

const Blacklist = mongoose.model("Blacklist", blacklistSchema);

module.exports = Blacklist;
