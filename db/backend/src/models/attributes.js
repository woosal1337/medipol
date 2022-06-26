const mongoose = require("mongoose");

const attributeSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  index: {
    type: Number,
    required: true,
  },
});

const Hat = mongoose.model("Hat", attributeSchema);
const Eye = mongoose.model("Eye", attributeSchema);
const Mouth = mongoose.model("Mouth", attributeSchema);
const Body = mongoose.model("Body", attributeSchema);
const Pet = mongoose.model("Pet", attributeSchema);
const Hand = mongoose.model("Hand", attributeSchema);

module.exports = {
  Hat,
  Eye,
  Mouth,
  Body,
  Pet,
  Hand,
};
