#!/usr/bin/env python

import sys

def even(n):
  return n%2 == 0

def filter(lst):
  even_list = []
  for i in lst:
    if even(i):
      even_list.append(i)
  return even_list
  
if __name__ == "__main__": 
 lst = [1,2,3,4,5,6,7,8,9,10]
 print filter(lst)
