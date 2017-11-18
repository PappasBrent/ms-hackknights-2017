var radius = 20;
var diameter = radius * 2;

// var noteInput = "CcDFfEeGaAbB";

var xPadding = radius;
var yPadding = radius;

var noteHeights = {
    'a': yPadding * 3 / 2,
    'A': yPadding * 3 / 2,
    'b': yPadding * 0,
    'B': yPadding * 0,
    'C': yPadding * 18 / 2,
    'c': yPadding * 18 / 2,
    'D': yPadding * 15 / 2,
    'E': yPadding * 12 / 2,
    'e': yPadding * 12 / 2,
    'F': yPadding * 9 / 2,
    'f': yPadding * 9 / 2,
    'G': yPadding * 6 / 2,
};

function circle(x, y, d) {
    ellipse(x, y, d / 2, d / 2);
}

function draw_lines(x, y, noteRadius, numNotes) {
    fill(155, 155, 155);
    for (var i = 0; i < 3; i++) {
        x1 = x;
        x2 = x + (numNotes * xPadding) + (numNotes * noteRadius);
        y1 = y + (i * (yPadding + noteRadius));
        y2 = y1;
        line(x1, y1, x2, y2);
    }
}

function draw_notes(x, y, notes, noteDiameter) {
    noteRadius = noteDiameter / 2;
    numNotes = notes.length;
    for (var i = 0; i < numNotes; i++) {
        currentNote = notes[i];
        posX = x + (i * xPadding) + (i * noteRadius) + noteRadius;
        posY = y + yPadding + noteHeights[currentNote] - noteRadius;
        if (currentNote == currentNote.toLowerCase()) {
            fill(255, 0, 128);
        }
        else {
            fill(0, 0, 0);
        }
        circle(posX, posY, noteRadius * 2);
    }
}

function preload() {
    noteInput = $("#note_characters").text();
    // noteInput = 'CcDFfEeGaAbB';
}

function setup() {
    var width = 640;
    var height = 480;
    createCanvas(width, height);
}

function draw() {
    fill(255, 255, 255);
    // rect(-1, -1, width + 1, height + 1);
    stroke(15, 15, 15);
    draw_lines(0, 100, diameter, noteInput.length);
    stroke(0, 0, 0);
    draw_notes(0, 100, noteInput, diameter);
}