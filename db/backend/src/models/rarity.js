const mongoose = require("mongoose");

const raritySchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
    unique: true,
  },
  score: {
    type: String,
    required: true,
  },
  percentage: {
    type: Number,
    required: true,
  },
  index: {
    type: Number,
    required: true,
  },
});

const Rarity = mongoose.model("Rarity", raritySchema);

module.exports = Rarity;
