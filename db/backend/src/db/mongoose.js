// Mongoose Import
const mongoose = require("mongoose");

// .env Configuration for MongoDB Connection
require("dotenv").config();

mongoose.connect(
  process.env.MONGO_DB_URL,
  {
    useNewUrlParser: true,
  },
  (err) => {
    if (err) console.error(err);
    else console.log("MongoDB connected");
  }
);
