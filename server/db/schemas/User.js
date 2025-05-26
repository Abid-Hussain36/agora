import mongoose from 'mongoose';
import {INTERESTS} from "../../../shared/enums.js";

const userSchema = new mongoose.Schema({
    username: {type: String, unique: true, required: true},
    email: {type: String, unique: true, required: true},
    password: {type: String, required: true},
    fName: {type: String, required: true},
    lName: {type: String},
    age: {type: Number, required: true},
    gender: {type: String, required: true},
    universityAffiliation: {type: String, required: true},
    profilePicture: {type: String},
    interests: {type: [{type: String, enum: INTERESTS}], default: []},
    friends: {type: [{type: mongoose.Schema.Types.ObjectId, ref: "User"}], default: []},
    // Stores lists of Event IDs that can populate actual events when needed
    hostedEvents: {type: [{type: mongoose.Schema.Types.ObjectId, ref: "Event"}], default: []},
    savedEvents: {type: [{type: mongoose.Schema.Types.ObjectId, ref: "Event"}], default: []},
    registeredEvents: {type: [{type: mongoose.Schema.Types.ObjectId, ref: "Event"}], default: []},
});

export default mongoose.model("User", userSchema);