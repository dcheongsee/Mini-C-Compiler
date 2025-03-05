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
str_2355908448:
.asciiz "Enter a number:\n"
# Allocated labels for virtual registers
.align 2
label_34_v11:
.space 4
.align 2
label_30_v9:
.space 4
.align 2
label_36_v12:
.space 4
.align 2
label_48_v18:
.space 4
.align 2
label_20_v3:
.space 4
.align 2
label_19_v4:
.space 4
.align 2
label_43_v15:
.space 4
.align 2
label_13_v0:
.space 4
.align 2
label_16_v2:
.space 4
.align 2
label_24_v6:
.space 4
.align 2
label_33_v10:
.space 4
.align 2
label_39_v13:
.space 4
.align 2
label_29_v7:
.space 4
.align 2
label_14_v1:
.space 4
.align 2
label_22_v5:
.space 4
.align 2
label_40_v14:
.space 4
.align 2
label_44_v16:
.space 4
.align 2
label_27_v8:
.space 4
.align 2
label_46_v17:
.space 4
# Allocated labels for virtual registers
.align 2
label_50_v19:
.space 4
.align 2
label_57_v23:
.space 4
.align 2
label_61_v25:
.space 4
.align 2
label_62_v26:
.space 4
.align 2
label_54_v21:
.space 4
.align 2
label_59_v24:
.space 4
.align 2
label_51_v20:
.space 4
.align 2
label_55_v22:
.space 4

.text
factorial:
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-12
addiu $sp,$sp,-12
# Original instruction: li v0,1
li $t5,1
la $t0,label_13_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-12
addiu $t5,$fp,-12
la $t0,label_14_v1
sw $t5,0($t0)
# Original instruction: sw v0,0(v1)
la $t5,label_13_v0
lw $t5,0($t5)
la $t4,label_14_v1
lw $t4,0($t4)
sw $t5,0($t4)
label_6_while_start:
# Original instruction: addiu v2,$fp,8
addiu $t5,$fp,8
la $t0,label_16_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_16_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_20_v3
sw $t4,0($t0)
# Original instruction: li v4,1
li $t5,1
la $t0,label_19_v4
sw $t5,0($t0)
# Original instruction: slt v5,v4,v3
la $t5,label_19_v4
lw $t5,0($t5)
la $t4,label_20_v3
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_22_v5
sw $t3,0($t0)
# Original instruction: beqz v5,label_7_while_exit
la $t5,label_22_v5
lw $t5,0($t5)
beqz $t5,label_7_while_exit
# Original instruction: addiu v6,$fp,-12
addiu $t5,$fp,-12
la $t0,label_24_v6
sw $t5,0($t0)
# Original instruction: lw v7,0(v6)
la $t5,label_24_v6
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_29_v7
sw $t4,0($t0)
# Original instruction: addiu v8,$fp,8
addiu $t5,$fp,8
la $t0,label_27_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_27_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_30_v9
sw $t4,0($t0)
# Original instruction: mul v10,v7,v9
la $t5,label_29_v7
lw $t5,0($t5)
la $t4,label_30_v9
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_33_v10
sw $t3,0($t0)
# Original instruction: addiu v11,$fp,-12
addiu $t5,$fp,-12
la $t0,label_34_v11
sw $t5,0($t0)
# Original instruction: sw v10,0(v11)
la $t5,label_33_v10
lw $t5,0($t5)
la $t4,label_34_v11
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v12,$fp,8
addiu $t5,$fp,8
la $t0,label_36_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_36_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_39_v13
sw $t4,0($t0)
# Original instruction: li v14,1
li $t5,1
la $t0,label_40_v14
sw $t5,0($t0)
# Original instruction: sub v15,v13,v14
la $t5,label_39_v13
lw $t5,0($t5)
la $t4,label_40_v14
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_43_v15
sw $t3,0($t0)
# Original instruction: addiu v16,$fp,8
addiu $t5,$fp,8
la $t0,label_44_v16
sw $t5,0($t0)
# Original instruction: sw v15,0(v16)
la $t5,label_43_v15
lw $t5,0($t5)
la $t4,label_44_v16
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_6_while_start
j label_6_while_start
label_7_while_exit:
# Original instruction: addiu v17,$fp,-12
addiu $t5,$fp,-12
la $t0,label_46_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_46_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_48_v18
sw $t4,0($t0)
# Original instruction: add $v0,v18,$zero
la $t5,label_48_v18
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_factorial
j label_5_return_factorial
label_5_return_factorial:
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: jr $ra
jr $ra

.text
.globl main
main:
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-12
addiu $sp,$sp,-12
# Original instruction: la v19,str_2355908448
la $t5,str_2355908448
la $t0,label_50_v19
sw $t5,0($t0)
# Original instruction: add $a0,v19,$zero
la $t5,label_50_v19
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v20,$zero,$zero
add $t5,$zero,$zero
la $t0,label_51_v20
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v21,$v0,$zero
add $t5,$v0,$zero
la $t0,label_54_v21
sw $t5,0($t0)
# Original instruction: addiu v22,$fp,-12
addiu $t5,$fp,-12
la $t0,label_55_v22
sw $t5,0($t0)
# Original instruction: sw v21,0(v22)
la $t5,label_54_v21
lw $t5,0($t5)
la $t4,label_55_v22
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v23,$fp,-12
addiu $t5,$fp,-12
la $t0,label_57_v23
sw $t5,0($t0)
# Original instruction: lw v24,0(v23)
la $t5,label_57_v23
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_59_v24
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v24,0($sp)
la $t5,label_59_v24
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal factorial
jal factorial
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v25,$v0,$zero
add $t5,$v0,$zero
la $t0,label_61_v25
sw $t5,0($t0)
# Original instruction: add $a0,v25,$zero
la $t5,label_61_v25
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v26,$zero,$zero
add $t5,$zero,$zero
la $t0,label_62_v26
sw $t5,0($t0)
label_9_return_main:
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: li $v0,10
li $v0,10
# Original instruction: syscall
syscall

