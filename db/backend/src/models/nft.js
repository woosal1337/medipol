const mongoose = require("mongoose");

const nftSchema = new mongoose.Schema(
  {
    tokenID: {
      type: Number,
      required: true,
      unique: true,
    },
    metadata: {
      type: String,
      required: true,
      unique: false,
    },
    minter: {
      type: String,
      required: true,
    },
    date: {
      type: Number,
      required: true,
    },
    transactionID: {
      type: String,
      required: false,
    },
  },
  { timestamps: true }
);

const NFT = mongoose.model("NFT", nftSchema);

module.exports = NFT;
