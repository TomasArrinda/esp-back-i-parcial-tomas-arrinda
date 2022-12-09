db.createUser(
    {
        user: "Tomas",
        pwd: "TestTest",
        roles: [
            {
                role: "readWrite",
                db: "db"
            }
        ]
    }
);