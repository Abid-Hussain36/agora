import { createRequire } from 'module';
const require = createRequire(import.meta.url);
const jwt = require('jsonwebtoken');


export const generateJWT = (userID) => {
    return jwt.sign({id: userID}, process.env.JWT_SECRET);
}

export const validateJWT = (req, res, next) => {
    try{
        const bearerToken = req.headers.authorization;
        if(!bearerToken || !bearerToken.startsWith("Bearer ")){
            res.status(400).json({message: "No or Invalid Token!"});
        }
        const token = bearerToken.split(" ")[1];
        const decodedToken = jwt.verify(token, process.env.JWT_SECRET);
        req.userID = decodedToken.id;
        next();
    } catch(err){
        res.status(500).json({message: "Token Validation Error!"});
    }
}