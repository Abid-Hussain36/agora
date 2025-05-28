import express from "express";
import User from "../db/schemas/User.js";
import bcrypt from "bcrypt";
import {generateJWT, validateJWT} from "../../shared/utils.js";


const userRouter = express.Router();

userRouter.post("/register", async (req, res) => {
    try{
        const {
            username,
            email,
            password,
            fName,
            lName,
            dob,
            gender,
            universityAffiliation,
            profilePicture,
            interests,
            friends,
            hostedEvents,
            savedEvents,
            registeredEvents
        } = req.body;

        const userSameUN = await User.findOne({username});
        if (userSameUN) {
            return res.status(409).json({message: "User with this username already exists!"});
        }

        const userSameEmail = await User.findOne({email});
        if (userSameEmail) {
            return res.status(409).json({message: "User with this email already exists!"});
        }

        const hashedPassword = await bcrypt.hash(password, 10);

        const newUser = new User({
            username,
            email,
            password: hashedPassword,
            fName,
            lName,
            dob,
            gender,
            universityAffiliation,
            profilePicture,
            interests,
            friends,
            hostedEvents,
            savedEvents,
            registeredEvents,
        });

        const savedUser = await newUser.save();
        const token = generateJWT(savedUser._id);
        return res.status(201).json({token});
    } catch(err){
        return res.status(500).json({message: `User Creation Error: + ${err}`});
    }
})

userRouter.post("/login", async (req, res) => {
    try{
        const {username, email, password} = req.body;
        let user = null;
        if(email !== ""){
            user = await User.findOne({email});
            if(!user){
                return res.status(404).json({message: "User with this email does not exist!"});
            }
        }
        else if(username !== ""){
            user = await User.findOne({username});
            if(!user){
                return res.status(404).json({message: "User with this username does not exist!"});
            }
        }
        else{
            return res.status(400).json({message: "Please enter a username or email!"});
        }

        const validPassword = await bcrypt.compare(password, user.password);
        if(!validPassword){
            return res.status(401).json({message: `Password Validation Error!`});
        }
        else{
            const token = generateJWT(user._id);
            return res.status(200).json({token});
        }
    } catch(err){
        return res.status(500).json({message: `User Login Error: ${err}`});
    }
})

userRouter.get("/", validateJWT, async (req, res) => {
    try{
        const userID = req.userID;
        const user = await User.findById(userID);
        if (!user) {
            return res.status(404).json({message: "User not found!"});
        }
        return res.status(200).json(user);
    } catch(err){
        return res.status(500).json({message: `User Retrieval Error: ${err}`});
    }
})

userRouter.put("/", validateJWT, async (req, res) => {
    try{
        const userID = req.userID;
        const newData = req.body;
        const updatedUser = await User.findByIdAndUpdate(userID, newData, {new: true});
        return res.status(200).json({message: JSON.stringify(updatedUser)});
    } catch(err){
        return res.status(500).json({message: `User Update Error: ${err}`});
    }
})

userRouter.delete("/", validateJWT, async (req, res) => {
    try{
        const userID = req.userID;
        await User.findByIdAndDelete(userID);
        return res.status(200).json({message: "User Deleted!"});
    } catch(err){
        return res.status(500).json({message: `User Deletion Error: ${err}`});
    }
})

export default userRouter;