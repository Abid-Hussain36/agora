import mongoose from "mongoose";

const connectDB = async() => {
    try{
        await mongoose.connect(process.env.MONGO_URI, {
            useNewUrlParser: true,
            useUnifiedTopology: true
        });
        console.log("Connected to MongoDB Atlas!");
    } catch(err){
        console.log("Mongo Connection Error: ", err);
    }
}

export default connectDB;