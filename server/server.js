import express from 'express';
import cors from 'cors';
import connectDB from "./db/connectDB.js";

const PORT = process.env.PORT || 5000;
const app = express();

app.use(cors());
app.use(express.json());

connectDB();

app.listen(PORT, () => console.log(`Server is running on port ${PORT}`));