var diameter = 40;
var radius = diameter / 2;

var el = $("#note_characters");

var noteInput = "CcDeEFfGaAbB";

noteInput = el.html();

// noteInput = prompt("Enter notes");

var xPadding = radius * 2;
var yPadding = radius * 3;

var noteHeights = {
    'a': radius * 2.5,
    'A': radius * 2.5,
    'b': radius,
    'B': radius,
    'C': radius * 9,
    'c': radius * 9,
    'D': radius * 8,
    'E': radius * 7,
    'e': radius * 7,
    'F': radius * 5.5,
    'f': radius * 5.5,
    'G': radius * 4,
};

function circle(x, y, d) {
    ellipse(x, y, d / 2, d / 2);
}

function draw_lines(x, y, noteRadius, numNotes) {
    fill(155, 155, 155);
    for (var i = 0; i < 3; i++) {
        x1 = x;
        x2 = x + (numNotes * xPadding);
        y1 = y + (i * yPadding);
        y2 = y1;
        line(x1, y1, x2, y2);
    }
}

function draw_notes(x, y, notes, noteRadius) {
    numNotes = notes.length;
    for (var i = 0; i < numNotes; i++) {
        currentNote = notes[i];
        posX = x + (i * xPadding) + noteRadius;
        posY = y + noteHeights[currentNote] - noteRadius;
        if (currentNote == currentNote.toLowerCase()) {
            fill(255, 0, 128);
        }
        else {
            fill(0, 0, 0);
        }
        circle(posX, posY, noteRadius);
    }
}

function setup() {
    var width = 640;
    var height = 480;
    createCanvas(width, height);
}

function draw() {
    fill(155, 155, 155);
    // rect(-1, -1, width + 1, height + 1);
    stroke(155, 155, 155);
    draw_lines(0, 100, radius, noteInput.length);
    stroke(0, 0, 0);
    draw_notes(0, 100, noteInput, radius);
}