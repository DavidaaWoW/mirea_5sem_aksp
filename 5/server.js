const mysql = require("mysql2");
const express = require("express");
var cors = require("cors");
var bodyParser = require("body-parser");

const app = express();

const pool = mysql.createPool({
  host: "localhost",
  user: "remoteuser",
  database: "ippdb",
  password: "1",
});

app.use(cors());
app.use(bodyParser.json());

app.get("/contact", function (req, res) {
  pool.query("SELECT * FROM contacts", function (err, data) {
    res.json(data);
  });
});

app.get("/contact/:id", function (req, res) {
  const id = req.params.id;
  const query = "SELECT * FROM contacts WHERE id=" + id;
  pool.query(query, function (err, data) {
    if (err) res.json(err);
    else res.json(data);
  });
});

// получаем отправленные данные и добавляем их в БД
app.post("/contact", function (req, res) {
  const name = req.body.name;

  pool.query(
    "INSERT INTO contacts ( name) VALUES (?)",
    [name],
    function (err, data) {
      if (!err) {
        res.json("Success!");
      } else {
        res.json(err);
      }
    }
  );
});

app.put("/contact/:id", function (req, res) {
  const id = req.params.id;
  const newName = req.body.name;
  const query = "UPDATE contacts SET name='" + newName + "' WHERE id=" + id;
  if (!newName) {
    res.json("[]");
    return;
  }

  pool.query(query, function (err, data) {
    if (err) res.json(err);
    else res.json("Success!");
  });
});

app.delete("/contact", function (req, res) {
  pool.query("DELETE FROM contacts", function (err) {
    if (err) res.json(err);
    else res.json("Success!");
  });
});

app.delete("/contact/:id", function (req, res) {
  const id = req.params.id;
  const query = "DELETE FROM contacts WHERE id=" + id;
  pool.query(query, function (err, data) {
    if (err) res.json(err);
    else res.json(query);
  });
});

app.listen(process.env.PORT || 3000, function () {
  console.log("Сервер ожидает подключения...");
});
