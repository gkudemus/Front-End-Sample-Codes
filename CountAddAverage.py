#!/usr/bin/python

import math

if __name__ == "__main__":

  count = 0
  input_number = 0
  isnotdone = 'not done'
  average = 0 
  sum = 0
   
  while isnotdone == 'not done':

    input  = raw_input('input a number:')
    
    try: 
      input_number = float(input)
      count = count+1    
      sum = sum+input_number
      average = sum/count 
      
      print sum
      print count
      print average      

      if input_number == 'done':
        isnotdone = 'done'
        input_number = 0
        average = 0
        count = 0
        break

    except:
      print 'DONE!'
      break 
