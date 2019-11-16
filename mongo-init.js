db.createUser(
        {
            user: "clearthoughts",
            pwd: "abc123",
            roles: [
                {
                    role: "readWrite",
                    db: "assaydb"
                }
            ]
        }
);