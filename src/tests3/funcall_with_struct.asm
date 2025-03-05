.data
.align 2
global_a:
.space 4
.align 2
global_b:
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
# Allocated labels for virtual registers
.align 2
label_34_v10:
.space 4
.align 2
label_33_v11:
.space 4
.align 2
label_14_v2:
.space 4
.align 2
label_25_v7:
.space 4
.align 2
label_17_v3:
.space 4
.align 2
label_19_v4:
.space 4
.align 2
label_29_v9:
.space 4
.align 2
label_24_v5:
.space 4
.align 2
label_27_v8:
.space 4
.align 2
label_36_v12:
.space 4
.align 2
label_11_v0:
.space 4
.align 2
label_16_v1:
.space 4
.align 2
label_22_v6:
.space 4
# Allocated labels for virtual registers
.align 2
label_57_v22:
.space 4
.align 2
label_38_v13:
.space 4
.align 2
label_47_v15:
.space 4
.align 2
label_49_v18:
.space 4
.align 2
label_45_v17:
.space 4
.align 2
label_40_v14:
.space 4
.align 2
label_54_v20:
.space 4
.align 2
label_56_v21:
.space 4
.align 2
label_59_v23:
.space 4
.align 2
label_64_v26:
.space 4
.align 2
label_63_v25:
.space 4
.align 2
label_50_v16:
.space 4
.align 2
label_61_v24:
.space 4
.align 2
label_52_v19:
.space 4

.text
make_pair:
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-16
addiu $sp,$sp,-16
# Original instruction: addiu v0,$fp,8
addiu $t5,$fp,8
la $t0,label_11_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_11_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_16_v1
sw $t4,0($t0)
# Original instruction: addiu v2,$fp,-16
addiu $t5,$fp,-16
la $t0,label_14_v2
sw $t5,0($t0)
# Original instruction: addiu v3,v2,0
la $t5,label_14_v2
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_17_v3
sw $t4,0($t0)
# Original instruction: sw v1,0(v3)
la $t5,label_16_v1
lw $t5,0($t5)
la $t4,label_17_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v4,$fp,12
addiu $t5,$fp,12
la $t0,label_19_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_19_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_24_v5
sw $t4,0($t0)
# Original instruction: addiu v6,$fp,-16
addiu $t5,$fp,-16
la $t0,label_22_v6
sw $t5,0($t0)
# Original instruction: addiu v7,v6,4
la $t5,label_22_v6
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_25_v7
sw $t4,0($t0)
# Original instruction: sw v5,0(v7)
la $t5,label_24_v5
lw $t5,0($t5)
la $t4,label_25_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v8,$fp,-16
addiu $t5,$fp,-16
la $t0,label_27_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_27_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_29_v9
sw $t4,0($t0)
# Original instruction: add $v0,v9,$zero
la $t5,label_29_v9
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_7_return_make_pair
j label_7_return_make_pair
label_7_return_make_pair:
# Original instruction: addiu v10,$fp,8
addiu $t5,$fp,8
la $t0,label_34_v10
sw $t5,0($t0)
# Original instruction: lw v11,0(v10)
la $t5,label_34_v10
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_33_v11
sw $t4,0($t0)
# Original instruction: sw v11,0($a0)
la $t5,label_33_v11
lw $t5,0($t5)
sw $t5,0($a0)
# Original instruction: lw v12,4(v10)
la $t5,label_34_v10
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_36_v12
sw $t4,0($t0)
# Original instruction: sw v12,4($a0)
la $t5,label_36_v12
lw $t5,0($t5)
sw $t5,4($a0)
# Original instruction: addiu $sp,$sp,16
addiu $sp,$sp,16
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
# Original instruction: addiu $sp,$sp,-16
addiu $sp,$sp,-16
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: li v13,7
li $t5,7
la $t0,label_38_v13
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v13,0($sp)
la $t5,label_38_v13
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v14,5
li $t5,5
la $t0,label_40_v14
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v14,0($sp)
la $t5,label_40_v14
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal make_pair
jal make_pair
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
# Original instruction: add v15,$a0,$zero
add $t5,$a0,$zero
la $t0,label_47_v15
sw $t5,0($t0)
# Original instruction: addiu v16,$fp,-16
addiu $t5,$fp,-16
la $t0,label_50_v16
sw $t5,0($t0)
# Original instruction: lw v17,0(v15)
la $t5,label_47_v15
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_45_v17
sw $t4,0($t0)
# Original instruction: sw v17,0(v16)
la $t5,label_45_v17
lw $t5,0($t5)
la $t4,label_50_v16
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: lw v18,4(v15)
la $t5,label_47_v15
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_49_v18
sw $t4,0($t0)
# Original instruction: sw v18,4(v16)
la $t5,label_49_v18
lw $t5,0($t5)
la $t4,label_50_v16
lw $t4,0($t4)
sw $t5,4($t4)
# Original instruction: addiu v19,$fp,-16
addiu $t5,$fp,-16
la $t0,label_52_v19
sw $t5,0($t0)
# Original instruction: addiu v20,v19,0
la $t5,label_52_v19
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_54_v20
sw $t4,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_54_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_56_v21
sw $t4,0($t0)
# Original instruction: add $a0,v21,$zero
la $t5,label_56_v21
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v22,$zero,$zero
add $t5,$zero,$zero
la $t0,label_57_v22
sw $t5,0($t0)
# Original instruction: addiu v23,$fp,-16
addiu $t5,$fp,-16
la $t0,label_59_v23
sw $t5,0($t0)
# Original instruction: addiu v24,v23,4
la $t5,label_59_v23
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_61_v24
sw $t4,0($t0)
# Original instruction: lw v25,0(v24)
la $t5,label_61_v24
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_63_v25
sw $t4,0($t0)
# Original instruction: add $a0,v25,$zero
la $t5,label_63_v25
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v26,$zero,$zero
add $t5,$zero,$zero
la $t0,label_64_v26
sw $t5,0($t0)
label_9_return_main:
# Original instruction: addiu $sp,$sp,16
addiu $sp,$sp,16
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

