#!/usr/bin/python

def RotateWord(string, number):
    rotate = number  % 26
    new_word = ''

    word=string.upper()
    for letter in word:
        ascii_val = ord(letter) + rotate
        if  ascii_val > 90:
            ascii_val -= 26
        new_word += chr(ascii_val)  
    print new_word


if __name__ == "__main__":

 word = raw_input("input word:")
 temp_number = raw_input("input number:")

 number = int(temp_number)
 
 RotateWord(word, number)
 
 
