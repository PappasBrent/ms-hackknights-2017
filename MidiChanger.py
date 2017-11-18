from subprocess import call
import csv

possibleNotes = ['C', 'c', 'D', 'e', 'E', 'F', 'f', 'G', 'a', 'A', 'b', 'B']

call("./Midicsv jingbell.mid jingbell.csv")

octave = 5

newNotes = open("Notes.out", "r").readline()

newRows = []

count = 0

with open('jingbell.csv', newline='') as inFile:
    inFileReader = csv.reader(inFile, delimiter=',', quotechar='|')
    for row in inFileReader:
        if ' Note_on_c' in row:
            # print('Found')
            # newVal = int(row[4])
            # newVal = 12
            # newVal += (5 * 12)
            # # row[4] = str(newVal)

            newNote = newNotes[count]
            newNote = possibleNotes.index(newNote)
            newNote += (octave * 12)
            row[4] = str(newNote)
            count+=1

        newRows.append(row)

print(count)

import csv
with open('jingbellNew.csv', 'w', newline='') as f:
    writer = csv.writer(f)
    writer.writerows(newRows)

call("./Csvmidi jingbellNew.csv jingbellNew.midi")
