.data
.align 2
global_c:
.space 1
.align 2
global_size:
.space 4
.align 2
global_s:
.space 4
.align 2
global_i:
.space 4

.text
getInput:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-12
li $t0,0
addiu $t1,$fp,-12
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
add $v0,$t0,$zero
j label_5_return_getInput
label_5_return_getInput:
addiu $sp,$sp,12
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
computeHeavy:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-64
li $t0,10
addiu $t1,$fp,-12
sw $t0,0($t1)
li $t0,20
addiu $t1,$fp,-16
sw $t0,0($t1)
li $t1,30
addiu $t0,$fp,-20
sw $t1,0($t0)
li $t0,40
addiu $t1,$fp,-24
sw $t0,0($t1)
li $t0,50
addiu $t1,$fp,-28
sw $t0,0($t1)
li $t1,60
addiu $t0,$fp,-32
sw $t1,0($t0)
li $t0,70
addiu $t1,$fp,-36
sw $t0,0($t1)
li $t0,80
addiu $t1,$fp,-40
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-16
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-44
sw $t0,0($t1)
addiu $t0,$fp,-20
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-24
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-48
sw $t1,0($t0)
addiu $t0,$fp,-28
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-32
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-52
sw $t1,0($t0)
addiu $t0,$fp,-36
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-40
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t1,$t0,$t1
addiu $t0,$fp,-56
sw $t1,0($t0)
addiu $t0,$fp,-44
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-48
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
mult $t1,$t0
mflo $t1
addiu $t0,$fp,-60
sw $t1,0($t0)
addiu $t0,$fp,-52
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-56
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
mult $t0,$t1
mflo $t0
addiu $t1,$fp,-64
sw $t0,0($t1)
addiu $t0,$fp,-60
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-64
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t0,$t0,$t1
add $v0,$t0,$zero
j label_7_return_computeHeavy
label_7_return_computeHeavy:
addiu $sp,$sp,64
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
.globl main
main:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-20
jal computeHeavy
addiu $sp,$sp,0
add $t1,$v0,$zero
addiu $t0,$fp,-12
sw $t1,0($t0)
jal getInput
addiu $sp,$sp,0
add $t0,$v0,$zero
addiu $t1,$fp,-16
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-16
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-20
sw $t1,0($t0)
addiu $t0,$fp,-20
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
addiu $t0,$fp,-20
lw $t0,0($t0)
add $v0,$t0,$zero
j label_9_return_main
label_9_return_main:
addiu $sp,$sp,20
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

