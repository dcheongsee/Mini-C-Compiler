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
label_56_v25:
.space 4
label_13_v1:
.space 4
label_28_v6:
.space 4
label_22_v8:
.space 4
label_29_v11:
.space 4
label_38_v16:
.space 4
label_48_v18:
.space 4
label_17_v5:
.space 4
label_34_v14:
.space 4
label_53_v23:
.space 4
label_49_v21:
.space 4
label_14_v4:
.space 4
label_11_v3:
.space 4
label_26_v10:
.space 4
label_10_v2:
.space 4
label_45_v19:
.space 4
label_46_v20:
.space 4
label_40_v12:
.space 4
label_37_v13:
.space 4
label_51_v22:
.space 4
label_41_v17:
.space 4
label_16_v0:
.space 4
label_23_v9:
.space 4
label_54_v24:
.space 4
label_25_v7:
.space 4
label_35_v15:
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
# Original instruction: li v0,10
li $t5,10
la $t0,label_16_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-20
addiu $t5,$fp,-20
la $t0,label_13_v1
sw $t5,0($t0)
# Original instruction: li v2,0
li $t5,0
la $t0,label_10_v2
sw $t5,0($t0)
# Original instruction: addiu v3,$zero,4
addiu $t5,$zero,4
la $t0,label_11_v3
sw $t5,0($t0)
# Original instruction: mul v4,v2,v3
la $t5,label_10_v2
lw $t5,0($t5)
la $t4,label_11_v3
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_14_v4
sw $t3,0($t0)
# Original instruction: add v5,v1,v4
la $t5,label_13_v1
lw $t5,0($t5)
la $t4,label_14_v4
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_17_v5
sw $t3,0($t0)
# Original instruction: sw v0,0(v5)
la $t5,label_16_v0
lw $t5,0($t5)
la $t4,label_17_v5
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v6,20
li $t5,20
la $t0,label_28_v6
sw $t5,0($t0)
# Original instruction: addiu v7,$fp,-20
addiu $t5,$fp,-20
la $t0,label_25_v7
sw $t5,0($t0)
# Original instruction: li v8,1
li $t5,1
la $t0,label_22_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$zero,4
addiu $t5,$zero,4
la $t0,label_23_v9
sw $t5,0($t0)
# Original instruction: mul v10,v8,v9
la $t5,label_22_v8
lw $t5,0($t5)
la $t4,label_23_v9
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_26_v10
sw $t3,0($t0)
# Original instruction: add v11,v7,v10
la $t5,label_25_v7
lw $t5,0($t5)
la $t4,label_26_v10
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_29_v11
sw $t3,0($t0)
# Original instruction: sw v6,0(v11)
la $t5,label_28_v6
lw $t5,0($t5)
la $t4,label_29_v11
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v12,30
li $t5,30
la $t0,label_40_v12
sw $t5,0($t0)
# Original instruction: addiu v13,$fp,-20
addiu $t5,$fp,-20
la $t0,label_37_v13
sw $t5,0($t0)
# Original instruction: li v14,2
li $t5,2
la $t0,label_34_v14
sw $t5,0($t0)
# Original instruction: addiu v15,$zero,4
addiu $t5,$zero,4
la $t0,label_35_v15
sw $t5,0($t0)
# Original instruction: mul v16,v14,v15
la $t5,label_34_v14
lw $t5,0($t5)
la $t4,label_35_v15
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_38_v16
sw $t3,0($t0)
# Original instruction: add v17,v13,v16
la $t5,label_37_v13
lw $t5,0($t5)
la $t4,label_38_v16
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_41_v17
sw $t3,0($t0)
# Original instruction: sw v12,0(v17)
la $t5,label_40_v12
lw $t5,0($t5)
la $t4,label_41_v17
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v18,$fp,-20
addiu $t5,$fp,-20
la $t0,label_48_v18
sw $t5,0($t0)
# Original instruction: li v19,1
li $t5,1
la $t0,label_45_v19
sw $t5,0($t0)
# Original instruction: addiu v20,$zero,4
addiu $t5,$zero,4
la $t0,label_46_v20
sw $t5,0($t0)
# Original instruction: mul v21,v19,v20
la $t5,label_45_v19
lw $t5,0($t5)
la $t4,label_46_v20
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_49_v21
sw $t3,0($t0)
# Original instruction: add v22,v18,v21
la $t5,label_48_v18
lw $t5,0($t5)
la $t4,label_49_v21
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_51_v22
sw $t3,0($t0)
# Original instruction: lw v23,0(v22)
la $t5,label_51_v22
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_53_v23
sw $t4,0($t0)
# Original instruction: add $a0,v23,$zero
la $t5,label_53_v23
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v24,$zero,$zero
add $t5,$zero,$zero
la $t0,label_54_v24
sw $t5,0($t0)
# Original instruction: li v25,0
li $t5,0
la $t0,label_56_v25
sw $t5,0($t0)
# Original instruction: add $v0,v25,$zero
la $t5,label_56_v25
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_main
j label_5_return_main
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

