const express = require("express")
const sqlite3 = require("sqlite3").verbose()
const bodyParser = require("body-parser")
const app = express()

app.use(bodyParser.json())
// app.use(bodyParser.urlencoded({ extended: true }))

const DATABASE = "students.db"
const db = new sqlite3.Database(DATABASE)

// app.post("/register", (req, res) => {
//      const { name, email, password } = req.body
//      db.run(
//           "INSERT INTO users (name, email, password) VALUES (?, ?, ?)",
//           [name, email, password],
//           (err, row) => {
//                if (err) {
//                     throw err
//                }
//                res.setHeader("Content-Type", "application/json")
//                // console.log(res.json(row.body))
//                res.json(row)
//           }
//      )
// })
app.post("/register", (req, res) => {
     const { name, phone, email, password } = req.body
     db.run(
          "INSERT INTO users (name, phone, email, password) VALUES (?, ?, ?, ?)",
          [name, phone, email, password],
          (err, rows) => {
               if (err) {
                    throw err
               }

               res.json(rows)
          }
     )
})
app.get("/login", (req, res) => {
     db.all("SELECT * FROM users", function (err, row) {
          console.log(row)
          res.json(row)
     })
})
app.put("/fogotpassword", (req, res) => {
     db.run(
          "UPDATE users SET password = ? WHERE `email`=?",
          [req.body.password, req.body.email],
          function (err, row) {
               console.log(req.body)
               console.log(row)
               res.json(row)
          }
     )
})

const port = 8080
app.listen(port, () => {
     console.log(`Server is running on port ${port}`)
})
