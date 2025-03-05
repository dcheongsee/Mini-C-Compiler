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
# Allocated labels for virtual registers
.align 2
label_13_v3:
.space 4
.align 2
label_23_v8:
.space 4
.align 2
label_9_v1:
.space 4
.align 2
label_15_v4:
.space 4
.align 2
label_28_v10:
.space 4
.align 2
label_47_v21:
.space 4
.align 2
label_48_v22:
.space 4
.align 2
label_8_v0:
.space 4
.align 2
label_33_v13:
.space 4
.align 2
label_42_v18:
.space 4
.align 2
label_35_v14:
.space 4
.align 2
label_37_v15:
.space 4
.align 2
label_45_v20:
.space 4
.align 2
label_22_v7:
.space 4
.align 2
label_38_v16:
.space 4
.align 2
label_43_v19:
.space 4
.align 2
label_19_v6:
.space 4
.align 2
label_12_v2:
.space 4
.align 2
label_18_v5:
.space 4
.align 2
label_29_v11:
.space 4
.align 2
label_25_v9:
.space 4
.align 2
label_32_v12:
.space 4
.align 2
label_40_v17:
.space 4

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
# Original instruction: li v0,2
li $t5,2
la $t0,label_8_v0
sw $t5,0($t0)
# Original instruction: li v1,3
li $t5,3
la $t0,label_9_v1
sw $t5,0($t0)
# Original instruction: add v2,v0,v1
la $t5,label_8_v0
lw $t5,0($t5)
la $t4,label_9_v1
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_12_v2
sw $t3,0($t0)
# Original instruction: addiu v3,$fp,-12
addiu $t5,$fp,-12
la $t0,label_13_v3
sw $t5,0($t0)
# Original instruction: sw v2,0(v3)
la $t5,label_12_v2
lw $t5,0($t5)
la $t4,label_13_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v4,$fp,-12
addiu $t5,$fp,-12
la $t0,label_15_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_15_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_18_v5
sw $t4,0($t0)
# Original instruction: li v6,10
li $t5,10
la $t0,label_19_v6
sw $t5,0($t0)
# Original instruction: mul v7,v5,v6
la $t5,label_18_v5
lw $t5,0($t5)
la $t4,label_19_v6
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_22_v7
sw $t3,0($t0)
# Original instruction: addiu v8,$fp,-16
addiu $t5,$fp,-16
la $t0,label_23_v8
sw $t5,0($t0)
# Original instruction: sw v7,0(v8)
la $t5,label_22_v7
lw $t5,0($t5)
la $t4,label_23_v8
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v9,$fp,-16
addiu $t5,$fp,-16
la $t0,label_25_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_25_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_28_v10
sw $t4,0($t0)
# Original instruction: li v11,5
li $t5,5
la $t0,label_29_v11
sw $t5,0($t0)
# Original instruction: sub v12,v10,v11
la $t5,label_28_v10
lw $t5,0($t5)
la $t4,label_29_v11
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_32_v12
sw $t3,0($t0)
# Original instruction: addiu v13,$fp,-20
addiu $t5,$fp,-20
la $t0,label_33_v13
sw $t5,0($t0)
# Original instruction: sw v12,0(v13)
la $t5,label_32_v12
lw $t5,0($t5)
la $t4,label_33_v13
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v14,$fp,-12
addiu $t5,$fp,-12
la $t0,label_35_v14
sw $t5,0($t0)
# Original instruction: lw v15,0(v14)
la $t5,label_35_v14
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_37_v15
sw $t4,0($t0)
# Original instruction: add $a0,v15,$zero
la $t5,label_37_v15
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v16,$zero,$zero
add $t5,$zero,$zero
la $t0,label_38_v16
sw $t5,0($t0)
# Original instruction: addiu v17,$fp,-16
addiu $t5,$fp,-16
la $t0,label_40_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_40_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_42_v18
sw $t4,0($t0)
# Original instruction: add $a0,v18,$zero
la $t5,label_42_v18
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v19,$zero,$zero
add $t5,$zero,$zero
la $t0,label_43_v19
sw $t5,0($t0)
# Original instruction: addiu v20,$fp,-20
addiu $t5,$fp,-20
la $t0,label_45_v20
sw $t5,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_45_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_47_v21
sw $t4,0($t0)
# Original instruction: add $a0,v21,$zero
la $t5,label_47_v21
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v22,$zero,$zero
add $t5,$zero,$zero
la $t0,label_48_v22
sw $t5,0($t0)
label_5_return_main:
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

