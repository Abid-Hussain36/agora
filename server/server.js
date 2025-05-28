import express from 'express';
import cors from 'cors';
import connectDB from "./db/connectDB.js";
import UserRoutes from "./routes/UserRoutes.js";
import EventRoutes from "./routes/EventRoutes.js";

const PORT = process.env.PORT || 5000;
const app = express();

app.use(cors());
app.use(express.json());

const setupConnections = async () => {
    try{
        await connectDB();
        app.use(UserRoutes);
        app.use(EventRoutes);
        app.listen(PORT, () => console.log(`Server is running on port ${PORT}`));
    } catch(err){
        console.log("Server Start Error: ", err);
    }
}

setupConnections();



