.data
global_c:
.align 2
.space 1
global_size:
.align 2
.space 4
global_s:
.align 2
.space 4
global_i:
.align 2
.space 4
# Allocated labels for virtual registers
label_12_v2:
.space 4
label_9_v0:
.space 4
label_15_v3:
.space 4
label_14_v1:
.space 4
label_17_v4:
.space 4
# Allocated labels for virtual registers
label_41_v16:
.space 4
label_36_v13:
.space 4
label_27_v9:
.space 4
label_20_v5:
.space 4
label_21_v6:
.space 4
label_33_v12:
.space 4
label_25_v8:
.space 4
label_37_v14:
.space 4
label_39_v15:
.space 4
label_42_v17:
.space 4
label_31_v11:
.space 4
label_24_v7:
.space 4
label_29_v10:
.space 4

.text
add:
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: addiu v0,$fp,8
addiu $t5,$fp,8
la $t0,label_9_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_9_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_14_v1
sw $t4,0($t0)
# Original instruction: addiu v2,$fp,12
addiu $t5,$fp,12
la $t0,label_12_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_12_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_15_v3
sw $t4,0($t0)
# Original instruction: add v4,v1,v3
la $t5,label_14_v1
lw $t5,0($t5)
la $t4,label_15_v3
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_17_v4
sw $t3,0($t0)
# Original instruction: add $v0,v4,$zero
la $t5,label_17_v4
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_add
j label_5_return_add
label_5_return_add:
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
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
# Original instruction: addiu $sp,$sp,-20
addiu $sp,$sp,-20
# Original instruction: li v5,5
li $t5,5
la $t0,label_20_v5
sw $t5,0($t0)
# Original instruction: addiu v6,$fp,-16
addiu $t5,$fp,-16
la $t0,label_21_v6
sw $t5,0($t0)
# Original instruction: sw v5,0(v6)
la $t5,label_20_v5
lw $t5,0($t5)
la $t4,label_21_v6
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v7,7
li $t5,7
la $t0,label_24_v7
sw $t5,0($t0)
# Original instruction: addiu v8,$fp,-20
addiu $t5,$fp,-20
la $t0,label_25_v8
sw $t5,0($t0)
# Original instruction: sw v7,0(v8)
la $t5,label_24_v7
lw $t5,0($t5)
la $t4,label_25_v8
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v9,$fp,-20
addiu $t5,$fp,-20
la $t0,label_27_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_27_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_29_v10
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v10,0($sp)
la $t5,label_29_v10
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v11,$fp,-16
addiu $t5,$fp,-16
la $t0,label_31_v11
sw $t5,0($t0)
# Original instruction: lw v12,0(v11)
la $t5,label_31_v11
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_33_v12
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v12,0($sp)
la $t5,label_33_v12
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal add
jal add
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: add v13,$v0,$zero
add $t5,$v0,$zero
la $t0,label_36_v13
sw $t5,0($t0)
# Original instruction: addiu v14,$fp,-12
addiu $t5,$fp,-12
la $t0,label_37_v14
sw $t5,0($t0)
# Original instruction: sw v13,0(v14)
la $t5,label_36_v13
lw $t5,0($t5)
la $t4,label_37_v14
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v15,$fp,-12
addiu $t5,$fp,-12
la $t0,label_39_v15
sw $t5,0($t0)
# Original instruction: lw v16,0(v15)
la $t5,label_39_v15
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_41_v16
sw $t4,0($t0)
# Original instruction: add $a0,v16,$zero
la $t5,label_41_v16
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v17,$zero,$zero
add $t5,$zero,$zero
la $t0,label_42_v17
sw $t5,0($t0)
label_7_return_main:
# Original instruction: addiu $sp,$sp,20
addiu $sp,$sp,20
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

