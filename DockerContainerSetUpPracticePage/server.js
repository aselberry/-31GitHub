var express = require('express');
var path = require('path');
var fs = require('fs');
var app = express();

// Set the path to the directory where your HTML file is located
var htmlPath = path.join(__dirname, "index.html");

app.get('/', function (req, res){
    res.sendFile(htmlPath);
});

app.get('/profile-picture', function (req, res){
    // Set the full path to your image file
    var imgPath = path.join(__dirname, '..', '..', '..', 'Desktop', 'DockterTestNana', 'profile-1.jpg');

    // Read the image file asynchronously
    fs.readFile(imgPath, function(err, img) {
        if (err) {
            console.log(err);
            res.status(500).send('Internal Server Error');
            return;
        }

        res.writeHead(200, {'Content-Type': 'image/jpg'});
        res.end(img, 'binary');
    });
});

app.listen(3000, function () {
    console.log("app listening on port 3000!");
});