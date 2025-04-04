.data
.align 2
global_n:
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
.align 2
global_i:
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
addiu $sp,$sp,-16
addiu $sp,$sp,-8
add $a0,$sp,$zero
li $t0,7
addiu $sp,$sp,-4
sw $t0,0($sp)
jal fibRec
addiu $sp,$sp,12
add $t2,$a0,$zero
addiu $t1,$fp,-16
lw $t0,0($t2)
sw $t0,0($t1)
lw $t0,4($t2)
sw $t0,4($t1)
addiu $t0,$fp,-16
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
li $t0,0
add $v0,$t0,$zero
j label_6_return_main
label_6_return_main:
addiu $sp,$sp,16
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

.text
fibRec:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-24
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,0
lw $t1,0($sp)
addiu $sp,$sp,4
add $t2,$t0,$zero
slt $t0,$t1,$t2
slt $t1,$t2,$t1
or $t0,$t0,$t1
xori $t0,$t0,1
beqz $t0,label_9_if_else
li $t1,0
addiu $t0,$fp,-16
addiu $t0,$t0,0
sw $t1,0($t0)
li $t1,1
addiu $t0,$fp,-16
addiu $t0,$t0,4
sw $t1,0($t0)
addiu $t0,$fp,-16
lw $t1,0($t0)
sw $t1,0($a0)
lw $t0,4($t0)
sw $t0,4($a0)
j label_8_return_fibRec
j label_10_if_end
label_9_if_else:
label_10_if_end:
addiu $sp,$sp,-8
add $a0,$sp,$zero
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,1
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
sub $t0,$t0,$t1
addiu $sp,$sp,-4
sw $t0,0($sp)
jal fibRec
addiu $sp,$sp,12
add $t2,$a0,$zero
addiu $t0,$fp,-24
lw $t1,0($t2)
sw $t1,0($t0)
lw $t1,4($t2)
sw $t1,4($t0)
addiu $t0,$fp,-24
lw $t1,4($t0)
addiu $t0,$fp,-16
addiu $t0,$t0,0
sw $t1,0($t0)
addiu $t0,$fp,-24
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-24
lw $t0,4($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-16
addiu $t1,$t1,4
sw $t0,0($t1)
addiu $t1,$fp,-16
lw $t0,0($t1)
sw $t0,0($a0)
lw $t0,4($t1)
sw $t0,4($a0)
j label_8_return_fibRec
label_8_return_fibRec:
addiu $sp,$sp,24
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

