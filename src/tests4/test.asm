.data
.align 2
global_arr:
.space 48
.align 2
global_i:
.space 4
.align 2
global_result:
.space 4
.align 2
global_weight:
.space 4
.align 2
global_adjust:
.space 4
.align 2
global_n:
.space 4
.align 2
global_val:
.space 4
.align 2
global_c:
.space 1
.align 2
global_size:
.space 4
.align 2
global_s:
.space 4
# Spill slot for v89
.align 2
label_18_spill_v89:
.space 4

.text
.globl main
main:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-8
li $t0,0
la $t1,global_i
sw $t0,0($t1)
li $t0,0
la $t1,global_result
sw $t0,0($t1)
li $t1,2
la $t0,global_weight
sw $t1,0($t0)
li $t0,3
la $t1,global_adjust
sw $t0,0($t1)
li $t1,5
la $t2,global_arr
li $t0,0
li $t3,4
mul $t0,$t0,$t3
add $t0,$t2,$t0
sw $t1,0($t0)
li $t3,7
la $t1,global_arr
li $t0,1
li $t2,4
mul $t0,$t0,$t2
add $t0,$t1,$t0
sw $t3,0($t0)
li $t1,9
la $t2,global_arr
li $t3,2
li $t0,4
mul $t0,$t3,$t0
add $t0,$t2,$t0
sw $t1,0($t0)
li $t1,11
la $t2,global_arr
li $t0,3
li $t3,4
mul $t0,$t0,$t3
add $t0,$t2,$t0
sw $t1,0($t0)
li $t1,13
la $t2,global_arr
li $t0,4
li $t3,4
mul $t0,$t0,$t3
add $t0,$t2,$t0
sw $t1,0($t0)
li $t1,15
la $t0,global_arr
li $t2,5
li $t3,4
mul $t2,$t2,$t3
add $t0,$t0,$t2
sw $t1,0($t0)
li $t2,17
la $t0,global_arr
li $t3,6
li $t1,4
mul $t1,$t3,$t1
add $t0,$t0,$t1
sw $t2,0($t0)
li $t2,19
la $t3,global_arr
li $t1,7
li $t0,4
mul $t0,$t1,$t0
add $t0,$t3,$t0
sw $t2,0($t0)
li $t3,21
la $t2,global_arr
li $t1,8
li $t0,4
mul $t0,$t1,$t0
add $t0,$t2,$t0
sw $t3,0($t0)
li $t2,23
la $t1,global_arr
li $t3,9
li $t0,4
mul $t0,$t3,$t0
add $t0,$t1,$t0
sw $t2,0($t0)
li $t1,25
la $t3,global_arr
li $t0,10
li $t2,4
mul $t0,$t0,$t2
add $t0,$t3,$t0
sw $t1,0($t0)
li $t2,27
la $t1,global_arr
li $t0,11
li $t3,4
mul $t0,$t0,$t3
add $t0,$t1,$t0
sw $t2,0($t0)
li $t0,12
addiu $sp,$sp,-4
sw $t0,0($sp)
jal weightedReduce
addiu $sp,$sp,4
add $t0,$v0,$zero
la $t1,global_result
sw $t0,0($t1)
la $t0,global_result
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
jal adjustResult
addiu $sp,$sp,4
add $t1,$v0,$zero
la $t0,global_result
sw $t1,0($t0)
la $t0,global_result
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t1,$zero,$zero
# store spilled v89 from v142
la $t0,label_18_spill_v89
sw $t1,0($t0)
li $t0,0
add $v0,$t0,$zero
j label_11_return_main
label_11_return_main:
addiu $sp,$sp,8
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

.text
weightedReduce:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-20
li $t0,0
addiu $t1,$fp,-12
sw $t0,0($t1)
li $t1,0
addiu $t0,$fp,-16
sw $t1,0($t0)
label_15_while_start:
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,8
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
slt $t0,$t0,$t1
beqz $t0,label_16_while_exit
la $t2,global_arr
addiu $t0,$fp,-12
lw $t0,0($t0)
li $t1,4
mul $t0,$t0,$t1
add $t0,$t2,$t0
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
la $t0,global_weight
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
mult $t1,$t0
mflo $t1
addiu $t0,$fp,-20
sw $t1,0($t0)
addiu $t0,$fp,-16
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-20
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t0,$t0,$t1
addiu $t1,$fp,-16
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,1
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t0,$t0,$t1
addiu $t1,$fp,-12
sw $t0,0($t1)
j label_15_while_start
label_16_while_exit:
addiu $t0,$fp,-16
lw $t0,0($t0)
add $v0,$t0,$zero
j label_14_return_weightedReduce
label_14_return_weightedReduce:
addiu $sp,$sp,20
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
adjustResult:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-12
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
la $t0,global_adjust
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
sub $t1,$t1,$t0
addiu $t0,$fp,-12
sw $t1,0($t0)
addiu $t0,$fp,-12
lw $t0,0($t0)
add $v0,$t0,$zero
j label_17_return_adjustResult
label_17_return_adjustResult:
addiu $sp,$sp,12
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

