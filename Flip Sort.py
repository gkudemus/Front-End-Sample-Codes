#!/usr/bin/env python

tutor = False 
 
def flipsort(data):
    
    if len(data) <= 1:
        return data
    if tutor: print()
    for size in range(len(data), 1, -1):
        maxindex = max(range(size), key=data.__getitem__)
        if maxindex+1 != size:
            if maxindex != 0:
                if tutor: print('With: %r flip %i'
                                % ( ' '.join(str(x) for x in data), maxindex+1 ))
                data[:maxindex+1] = reversed(data[:maxindex+1])
            if tutor: print('With: %r flip %i'
                                % ( ' '.join(str(x) for x in data), size ))
            data[:size] = reversed(data[:size])
    if tutor: print()

if __name__ == '__main__':
    import random
 
    tutor = True
    
    data = list('12345')
    while data == sorted(data):
        random.shuffle(data)
    print('Original List: %r' % ' '.join(data))
    flipsort(data)
    print('Sorted List: %r' % ' '.join(data))
