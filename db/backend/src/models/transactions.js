const mongoose = require("mongoose");

const transactionSchema = new mongoose.Schema(
  {
    uri: {
      type: String,
      required: true,
      unique: true,
    },
    timestamp: {
      type: Number,
      required: true,
    },
  },
  { timestamps: true }
);

const Transactions = mongoose.model("Transactions", transactionSchema);

module.exports = Transactions;
