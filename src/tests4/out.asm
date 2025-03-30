.data
.align 2
global_a:
.space 4
.align 2
global_b:
.space 4
.align 2
global_x:
.space 4
.align 2
global_y:
.space 4
.align 2
global_z:
.space 4
.align 2
global_temp:
.space 4
.align 2
global_final_result:
.space 4
.align 2
global_i:
.space 4
.align 2
global_j:
.space 4
.align 2
global_sum:
.space 4
.align 2
global_arr:
.space 800
.align 2
global_c:
.space 1
.align 2
global_size:
.space 4
.align 2
global_s:
.space 4
str_572853306:
.asciiz "Tricky edge jumps test passed"

.text
empty_func:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-8
li $t0,0
add $v0,$t0,$zero
j label_15_return_empty_func
label_15_return_empty_func:
addiu $sp,$sp,8
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
tricky_func:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-16
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,12
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
sub $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,0
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
slt $t0,$t0,$t1
beqz $t0,label_18_if_else
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,12
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
sub $t0,$t0,$t1
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,7
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-12
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,123
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
mult $t0,$t1
mflo $t1
addiu $t0,$fp,-16
sw $t1,0($t0)
j label_19_if_end
label_18_if_else:
addiu $t0,$fp,12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,8
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
sub $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,3
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t1,$t0,$t1
addiu $t0,$fp,-12
sw $t1,0($t0)
label_19_if_end:
addiu $t0,$fp,-12
lw $t0,0($t0)
add $v0,$t0,$zero
j label_17_return_tricky_func
label_17_return_tricky_func:
addiu $sp,$sp,16
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
addiu $sp,$sp,-8
li $t0,100
la $t1,global_x
sw $t0,0($t1)
li $t1,150
la $t0,global_y
sw $t1,0($t0)
li $t0,200
la $t1,global_z
sw $t0,0($t1)
jal empty_func
addiu $sp,$sp,0
add $t0,$v0,$zero
la $t1,global_temp
sw $t0,0($t1)
la $t0,global_y
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
la $t0,global_x
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
jal tricky_func
addiu $sp,$sp,8
add $t1,$v0,$zero
la $t0,global_temp
sw $t1,0($t0)
li $t0,0
la $t1,global_sum
sw $t0,0($t1)
li $t0,0
la $t1,global_i
sw $t0,0($t1)
label_22_while_start:
la $t0,global_i
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,200
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
slt $t0,$t1,$t0
beqz $t0,label_23_while_exit
la $t0,global_i
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,1
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t2,$t0,$t1
la $t1,global_arr
la $t0,global_i
lw $t3,0($t0)
li $t0,4
mul $t0,$t3,$t0
add $t0,$t1,$t0
sw $t2,0($t0)
la $t0,global_i
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,1
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
la $t0,global_i
sw $t1,0($t0)
j label_22_while_start
label_23_while_exit:
li $t1,0
la $t0,global_i
sw $t1,0($t0)
label_24_while_start:
la $t0,global_i
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,200
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
slt $t0,$t1,$t0
beqz $t0,label_25_while_exit
li $t1,0
la $t0,global_j
sw $t1,0($t0)
label_26_while_start:
la $t0,global_j
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,200
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
slt $t0,$t1,$t0
beqz $t0,label_27_while_exit
la $t0,global_z
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
la $t2,global_arr
la $t0,global_i
lw $t1,0($t0)
li $t0,4
mul $t0,$t1,$t0
add $t0,$t2,$t0
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
jal tricky_func
addiu $sp,$sp,8
add $t1,$v0,$zero
la $t0,global_temp
sw $t1,0($t0)
la $t0,global_sum
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
la $t0,global_temp
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
la $t1,global_sum
sw $t0,0($t1)
la $t0,global_j
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,1
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
la $t0,global_j
sw $t1,0($t0)
j label_26_while_start
label_27_while_exit:
la $t0,global_i
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,1
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
la $t1,global_i
sw $t0,0($t1)
j label_24_while_start
label_25_while_exit:
la $t0,global_sum
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
la $t0,global_temp
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
la $t0,global_final_result
sw $t1,0($t0)
la $t0,global_final_result
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
la $t0,str_572853306
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
li $t0,0
add $v0,$t0,$zero
j label_21_return_main
label_21_return_main:
addiu $sp,$sp,8
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

