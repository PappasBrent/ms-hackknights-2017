import re, math

line = "1926 On ch=1 n=60 v=114"

trackPattern = r"(\d+) On ch=(\d+) n=(\d+) v=(\d+)"

# print(re.findall(trackPattern, line))




midiText = open("jinglebells.txt", "r")

timesIn = open("Times.in", "w")
channelsIn = open("Channels.in", 'w')
notesIn = open("Notes.in", 'w')
volumesIn = open("Volumes.in", 'w')


possibleNotes = ['C', 'c', 'D', 'e', 'E', 'F', 'f', 'G', 'a', 'A', 'b', 'B']

times=[]
channels=[]
notes = ''
volumes=[]

lines = list(midiText.readlines())

for line in lines:
    vals = re.findall(trackPattern, line)
    if not vals:
        continue
    if len(vals[0]) < 4:
        continue

    t=vals[0][0]
    ch=vals[0][1]
    n=int(vals[0][2])
    v=vals[0][3]

    octave = math.floor(n/12)
    pVal = n%12

    times.append(t)
    channels.append(ch)
    notes+=possibleNotes[pVal]
    volumes.append(v)

print(notes)

timesIn.write('\n'.join(times))
channelsIn.write('\n'.join(channels))
notesIn.write(notes)
volumesIn.write('\n'.join(volumes))

midiText.close()

timesIn.close()
channelsIn.close()
notesIn.close()
volumesIn.close()
