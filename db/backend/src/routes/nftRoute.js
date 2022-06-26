const router = require("express").Router();

// MongoDB NFT Model
const NFT = require("../models/nft");
const Metadata = require("../models/metadata");
const { Hat, Pet, Body, Hand, Mouth, Eye } = require("../models/attributes");
const Rarity = require("../models/rarity");

const mongoose = require("mongoose");

router.get("/:tokenID", async (req, res) => {
  const tokenID = req.params.tokenID;
  const n = await NFT.find({ tokenID });

  if (n.length === 0) {
    res.status(404).send("NFT not found");
    return;
  }

  var id = mongoose.mongo.ObjectId(String(n[0].metadata));

  const meta = await Metadata.find({
    _id: id,
  });

  const rarity = await Rarity.find({
    _id: meta[0].rarity,
  });

  const hat = await Hat.find({
    index: meta[0].hatID,
  });
  const eye = await Eye.find({
    index: meta[0].eyeID,
  });
  const mouth = await Mouth.find({
    index: meta[0].mouthID,
  });
  const hand = await Hand.find({
    index: meta[0].handID,
  });
  const body = await Body.find({
    index: meta[0].bodyID,
  });
  const pet = await Pet.find({
    index: meta[0].petID,
  });

  var fullUrl = req.protocol + "://" + req.get("host") + "/" + tokenID + ".png";

  const resNFT = {
    fullUrl,
    tokenID: n[0].tokenID,
    owner: n[0].owner,
    hat: hat[0].name,
    eye: eye[0].name,
    mouth: mouth[0].name,
    hand: hand[0].name,
    body: body[0].name,
    pet: pet[0].name,
    rarity: rarity[0].name,
    rarityScore: rarity[0].score,
    rarityPercentage: rarity[0].percentage,
  };

  res.send(resNFT);
});

module.exports = router;
