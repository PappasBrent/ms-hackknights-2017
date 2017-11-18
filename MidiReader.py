
import re, math

line = "1926 On ch=1 n=60 v=114"
line = "2, 192, Note_on_c, 0, 71, 47"

trackPattern = r"(\d+) On ch=(\d+) n=(\d+) v=(\d+)"
trackPattern = r"Note_on_c, \d+, (\d+),"

print(re.findall(trackPattern, line))




midiText = open("jingbell.txt", "r")

# timesIn = open("Times.in", "w")
# channelsIn = open("Channels.in", 'w')
notesIn = open("Notes.in", 'w')
# volumesIn = open("Volumes.in", 'w')


possibleNotes = ['C', 'c', 'D', 'e', 'E', 'F', 'f', 'G', 'a', 'A', 'b', 'B']

# times=[]
# channels=[]
notes = ''
# volumes=[]

lines = list(midiText.readlines())

for line in lines:
    vals = re.findall(trackPattern, line)
    # print(vals)
    if not vals:
        continue
    if len(vals[0]) < 1:
        continue

    # t=vals[0][0]
    # ch=vals[0][1]
    n=int(vals[0][0])
    # v=vals[0][3]

    octave = math.floor(n/12)
    pVal = n%12

    # times.append(t)
    # channels.append(ch)
    # volumes.append(v)
    notes+=possibleNotes[pVal]

print(notes)

# timesIn.write('\n'.join(times))
# channelsIn.write('\n'.join(channels))
# volumesIn.write('\n'.join(volumes))


notesIn.write(notes)

midiText.close()

# timesIn.close()
# channelsIn.close()
# notesIn.close()
# volumesIn.close()
