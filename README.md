# MKS22X-Queens


will do the same process as the codingbat hw
check to see if board is solved for sure - return true
check to see if board is not solved for sure - return false   } by checking if last row has -1 or not (working on board from top to bottom)

if those two are not true then do work:
loop through the current row looking for possible slots (0)
if there is one then call helper again (change board in parameter)

add and remove queen methods should change board to change the slots that are now "covered" by the new/ removed queen
