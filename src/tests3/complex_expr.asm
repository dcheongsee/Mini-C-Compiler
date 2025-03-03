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
label_17_v4:
.space 4
label_9_v0:
.space 4
label_14_v1:
.space 4
label_15_v3:
.space 4
label_12_v2:
.space 4
# Allocated labels for virtual registers
label_39_v15:
.space 4
label_33_v12:
.space 4
label_53_v22:
.space 4
label_57_v24:
.space 4
label_62_v27:
.space 4
label_25_v8:
.space 4
label_44_v18:
.space 4
label_61_v26:
.space 4
label_20_v5:
.space 4
label_56_v23:
.space 4
label_31_v11:
.space 4
label_24_v7:
.space 4
label_50_v21:
.space 4
label_47_v20:
.space 4
label_35_v13:
.space 4
label_38_v14:
.space 4
label_41_v16:
.space 4
label_52_v17:
.space 4
label_30_v10:
.space 4
label_59_v25:
.space 4
label_49_v19:
.space 4
label_21_v6:
.space 4
label_27_v9:
.space 4
label_64_v28:
.space 4

.text
foo:
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
# Original instruction: mul v4,v1,v3
la $t5,label_14_v1
lw $t5,0($t5)
la $t4,label_15_v3
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_17_v4
sw $t3,0($t0)
# Original instruction: add $v0,v4,$zero
la $t5,label_17_v4
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_foo
j label_5_return_foo
label_5_return_foo:
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
# Original instruction: li v5,2
li $t5,2
la $t0,label_20_v5
sw $t5,0($t0)
# Original instruction: addiu v6,$fp,-12
addiu $t5,$fp,-12
la $t0,label_21_v6
sw $t5,0($t0)
# Original instruction: sw v5,0(v6)
la $t5,label_20_v5
lw $t5,0($t5)
la $t4,label_21_v6
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v7,3
li $t5,3
la $t0,label_24_v7
sw $t5,0($t0)
# Original instruction: addiu v8,$fp,-16
addiu $t5,$fp,-16
la $t0,label_25_v8
sw $t5,0($t0)
# Original instruction: sw v7,0(v8)
la $t5,label_24_v7
lw $t5,0($t5)
la $t4,label_25_v8
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v9,$fp,-16
addiu $t5,$fp,-16
la $t0,label_27_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_27_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_30_v10
sw $t4,0($t0)
# Original instruction: li v11,2
li $t5,2
la $t0,label_31_v11
sw $t5,0($t0)
# Original instruction: add v12,v10,v11
la $t5,label_30_v10
lw $t5,0($t5)
la $t4,label_31_v11
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_33_v12
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v12,0($sp)
la $t5,label_33_v12
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v13,$fp,-12
addiu $t5,$fp,-12
la $t0,label_35_v13
sw $t5,0($t0)
# Original instruction: lw v14,0(v13)
la $t5,label_35_v13
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_38_v14
sw $t4,0($t0)
# Original instruction: li v15,1
li $t5,1
la $t0,label_39_v15
sw $t5,0($t0)
# Original instruction: add v16,v14,v15
la $t5,label_38_v14
lw $t5,0($t5)
la $t4,label_39_v15
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_41_v16
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v16,0($sp)
la $t5,label_41_v16
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal foo
jal foo
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: add v17,$v0,$zero
add $t5,$v0,$zero
la $t0,label_52_v17
sw $t5,0($t0)
# Original instruction: addiu v18,$fp,-12
addiu $t5,$fp,-12
la $t0,label_44_v18
sw $t5,0($t0)
# Original instruction: lw v19,0(v18)
la $t5,label_44_v18
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_49_v19
sw $t4,0($t0)
# Original instruction: addiu v20,$fp,-16
addiu $t5,$fp,-16
la $t0,label_47_v20
sw $t5,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_47_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_50_v21
sw $t4,0($t0)
# Original instruction: mul v22,v19,v21
la $t5,label_49_v19
lw $t5,0($t5)
la $t4,label_50_v21
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_53_v22
sw $t3,0($t0)
# Original instruction: sub v23,v17,v22
la $t5,label_52_v17
lw $t5,0($t5)
la $t4,label_53_v22
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_56_v23
sw $t3,0($t0)
# Original instruction: addiu v24,$fp,-20
addiu $t5,$fp,-20
la $t0,label_57_v24
sw $t5,0($t0)
# Original instruction: sw v23,0(v24)
la $t5,label_56_v23
lw $t5,0($t5)
la $t4,label_57_v24
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v25,$fp,-20
addiu $t5,$fp,-20
la $t0,label_59_v25
sw $t5,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_59_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_61_v26
sw $t4,0($t0)
# Original instruction: add $a0,v26,$zero
la $t5,label_61_v26
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v27,$zero,$zero
add $t5,$zero,$zero
la $t0,label_62_v27
sw $t5,0($t0)
# Original instruction: li v28,0
li $t5,0
la $t0,label_64_v28
sw $t5,0($t0)
# Original instruction: add $v0,v28,$zero
la $t5,label_64_v28
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_7_return_main
j label_7_return_main
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

