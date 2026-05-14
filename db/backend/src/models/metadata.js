const mongoose = require("mongoose");

const metadataSchema = new mongoose.Schema(
  {
    rarity: {
      type: String,
      required: true,
      unique: false,
    },
    hatID: {
      type: Number,
      required: true,
    },
    eyeID: {
      type: String,
      required: true,
    },
    mouthID: {
      type: String,
      required: true,
    },
    bodyID: {
      type: String,
      required: true,
    },
    petID: {
      type: String,
      required: true,
    },
    handID: {
      type: String,
      required: true,
    },
  },
  { timestamps: true }
);

const Metadata = mongoose.model("Metadata", metadataSchema);

module.exports = Metadata;
