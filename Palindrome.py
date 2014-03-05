#!/usr/bin/env python

import sys

if __name__ == "__main__":

  string = raw_input('input string:')
  # print string

  checkstring = string[::-1]

  if checkstring == string:
    print 'it is a palindrome'

  else:
    print 'it is not a palindrome'

