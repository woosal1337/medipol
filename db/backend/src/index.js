// Express.js Import for creating REST-API Server with Node.jss
const express = require("express");

const path = require("path");

// To Avoid CORS Error
const cors = require("cors");

// Creating an application for handling REST-API requests and responses
const app = express();
app.use(cors());

// Importing contract instance for listening event from blockchain
require("./contract/index");

// Importing mongoose.js file for connecting to MongoDB when server restart
require("./db/mongoose");

// Importing models for create initial database
const Rarity = require("./models/rarity");
const User = require("./models/user");
const Project = require("./models/project");
const { Hand, Pet, Body, Mouth, Eye, Hat } = require("./models/attributes");

// Importing routes for handling REST-API requests
const nftRoute = require("./routes/nftRoute");

const initialDBCreator = async () => {
  const rarities = await Rarity.find({});
  const users = await User.find({});
  const projects = await Project.find({});
  const hands = await Hand.find({});
  const pets = await Pet.find({});
  const bodies = await Body.find({});
  const mouths = await Mouth.find({});
  const eyes = await Eye.find({});
  const hats = await Hat.find({});

  if (hands.length == 0) {
    const hands1 = new Hand({
      name: "None",
      index: 0,
    });
    const hands2 = new Hand({
      name: "Ice Mage",
      index: 1,
    });

    await hands1.save();
    await hands2.save();
  }

  if (pets.length == 0) {
    const pets1 = new Pet({
      name: "None",
      path: "",
      index: 0,
    });
    const pets2 = new Pet({
      name: "Turtle",
      path: "",
      index: 1,
    });

    await pets1.save();
    await pets2.save();
  }

  if (bodies.length == 0) {
    const bodies1 = new Body({
      name: "None",
      index: 0,
    });
    const bodies2 = new Body({
      name: "Bikini",
      index: 1,
    });

    await bodies1.save();
    await bodies2.save();
  }

  if (mouths.length == 0) {
    const mouths1 = new Mouth({
      name: "None",
      index: 0,
    });
    const mouths2 = new Mouth({
      name: "Tongue Out",
      index: 1,
    });

    await mouths1.save();
    await mouths2.save();
  }

  if (eyes.length == 0) {
    const eyes1 = new Eye({
      name: "None",
      index: 0,
    });
    const eyes2 = new Eye({
      name: "Eye Band",
      index: 1,
    });

    await eyes1.save();
    await eyes2.save();
  }

  if (hats.length == 0) {
    const hats1 = new Hat({
      name: "None",
      index: 0,
    });
    const hats2 = new Hat({
      name: "Mage Hat",
      index: 1,
    });

    await hats1.save();
    await hats2.save();
  }

  let id;
  if (rarities.length === 0) {
    const r1 = new Rarity({
      name: "Common",
      score: "10-50",
      percentage: 20,
      index: 0,
    });
    const r2 = new Rarity({
      name: "Rare",
      score: "50-80",
      percentage: 20,
      index: 1,
    });
    const r3 = new Rarity({
      name: "Ultra Rare",
      score: "80-100",
      percentage: 20,
      index: 2,
    });

    await r1.save();
    await r2.save();
    await r3.save();

    console.log("Initial Rarity collection created");
  } else console.log("Rarities collection already exists");

  if (users.length === 0) {
    const u1 = new User({
      address: "0x0000000000000000000000000000000000000000",
      ownedNFTAmount: 0,
      username: "berat",
    });

    id = u1._id.toString();

    const u2 = new User({
      address: "0x0000000000000000000000000000000000000001",
      ownedNFTAmount: 0,
      username: "vusal",
    });

    await u1.save();
    await u2.save();

    console.log("Initial User collection created");
  } else console.log("Users collection already exists");

  if (projects.length === 0) {
    const p = new Project({
      name: "DB Project",
      admin: id,
    });

    await p.save();

    console.log("Initial Project collection created");
  } else console.log("Projects collection already exists");
};

initialDBCreator();

// Express.js configuration for using JSON as a data formats
app.use(express.static(path.join(__dirname, "nfts")));
app.use(express.json());

app.use("/api/nft", nftRoute);

// Global PORT variable for server to listen on
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
