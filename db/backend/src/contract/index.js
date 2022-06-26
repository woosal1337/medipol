// Web3 Instance for listening event from blockchain
const Web3 = require("web3");

// ABI of contract
const { abi } = require("./abi/NFT.json");

// Creating an websocket provider for listening event from NFT Contract
const provider = new Web3.providers.WebsocketProvider("ws://127.0.0.1:8545");

const web3 = new Web3(provider);

// Util library for creating NFT images
const sharp = require("sharp");
const path = require("path");

// Creating contract instance
const contract = new web3.eth.Contract(
  abi,
  "0x5FbDB2315678afecb367f032d93F642f64180aa3"
);

const Blacklist = require("../models/blacklist");
const Whitelist = require("../models/whitelist");
const Rarity = require("../models/rarity");
const Metadata = require("../models/metadata");
const NFT = require("../models/nft");

contract.events
  .BlacklistAdded({}, (error, event) => {
    if (error) console.log(error);
  })
  .on("data", async (event) => {
    const values = event.returnValues;
    const address = values._blacklistedAddress;

    const blacklist = new Blacklist({
      address,
    });

    await blacklist.save();
    console.log("Blacklisted address added: " + address);
  });

contract.events
  .WhitelistAdded({}, (error, event) => {
    if (error) console.log(error);
  })
  .on("data", async (event) => {
    const values = event.returnValues;
    const address = values._whitelistedAddress;
    const amount = values.amount;

    const whitelist = new Whitelist({
      address,
      amount,
    });

    await whitelist.save();
    console.log(
      "Whitelisted address added: " + address + " with amount: " + amount
    );
  });

contract.events
  .NFTCreated({}, (error, event) => {
    if (error) console.log(error);
  })
  .on("data", async (event) => {
    const values = event.returnValues;
    const nft = values._snft;

    const tokenID = nft.id;
    const owner = nft.owner;

    const hatID = parseInt(nft.hatID);
    const petID = nft.petID;
    const bodyID = nft.bodyID;
    const mouthID = nft.mouthID;
    const eyeID = nft.eyeID;
    const handID = nft.handID;

    const randomRarityIndex = tokenID % 3;
    const rarity = await Rarity.findOne({
      index: randomRarityIndex,
    });

    const metadata = new Metadata({
      rarity: rarity.id,
      hatID,
      eyeID,
      mouthID,
      bodyID,
      petID,
      handID,
    });

    await metadata.save();

    const nftModel = new NFT({
      tokenID,
      metadata: metadata.id.toString(),
      minter: owner,
      date: Math.floor(Date.now() / 1000),
    });

    await nftModel.save();

    const baseImage = path.join(__dirname, "../images/base/0.png");
    const handImage = path.join(__dirname, `../images/hands/${handID}.png`);
    const mouthImage = path.join(__dirname, `../images/mouths/${mouthID}.png`);
    const eyeImage = path.join(__dirname, `../images/eyes/${eyeID}.png`);
    const bodyImage = path.join(__dirname, `../images/bodies/${bodyID}.png`);
    const petImage = path.join(__dirname, `../images/pets/${petID}.png`);
    const hatImage = path.join(__dirname, `../images/hats/${hatID}.png`);

    const outputPath = path.join(__dirname, `../nfts/${tokenID}.png`);

    await sharp(baseImage)
      .composite([
        {
          input: eyeImage,
        },
        {
          input: hatImage,
        },
        {
          input: bodyImage,
        },
        {
          input: mouthImage,
        },
        {
          input: handImage,
        },
        {
          input: petImage,
        },
      ])
      .toFile(outputPath);

    console.log("Successfully created NFT: " + tokenID);
  });

module.exports = contract;
