db.createUser(
    {
        user: "usrspotifymongo",
        pwd: "pwdspotifymongo",
        roles: [
            {
                role: "readWrite",
                db: "spotifydevmongo"
            }
        ]
    }
);