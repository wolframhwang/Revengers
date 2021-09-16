'''
i,j에서 0이면
(i,0) (0,j) 에 0을 표시해서 
나중에 (i,0) 이 0 이면 (i,0..len(matrix[i])) 를 모두 0으로만들고
(0,j)이 0이면 (0.. len(matrix), j ) 를 모두 0으로 만드는 식으로하자.
단, 이렇게하면 맨 왼쪽과 맨 위쪽은 0처리를 하기 힘들어지니 0열과 0행에 대해서는 따로 처리해두도록 하자
'''
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        rowZero = False
        colZero = False
        for i in range(0, len(matrix)):
            rowZero = rowZero | (matrix[i][0] == 0)
        for j in range(0, len(matrix[0])):
            colZero = colZero | (matrix[0][j] == 0)
        for i in range(1, len(matrix)):
            for j in range(1, len(matrix[0])):
                if(matrix[i][j] == 0): 
                    matrix[0][j] = 0
                    matrix[i][0] = 0
        for i in range(1, len(matrix)):
            if(matrix[i][0] == 0):
                for j in range(1, len(matrix[i])): matrix[i][j] = 0
        for j in range(1, len(matrix[0])):
            if(matrix[0][j] == 0):
                for i in range(1, len(matrix)): matrix[i][j] = 0
        if rowZero:
            for i in range(0, len(matrix)): matrix[i][0] = 0
        if colZero:
            for j in range(0, len(matrix[0])): matrix[0][j] = 0
                 
        
