.data
.align 2
global_nums:
.space 12
.align 2
global_label:
.space 1
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
label_38_v14:
.space 4
label_64_v24:
.space 4
label_65_v27:
.space 4
label_62_v26:
.space 4
label_24_v0:
.space 4
label_19_v5:
.space 4
label_25_v7:
.space 4
label_21_v3:
.space 4
label_46_v18:
.space 4
label_34_v12:
.space 4
label_30_v10:
.space 4
label_54_v22:
.space 4
label_56_v16:
.space 4
label_44_v17:
.space 4
label_57_v23:
.space 4
label_40_v8:
.space 4
label_28_v9:
.space 4
label_50_v20:
.space 4
label_12_v1:
.space 4
label_14_v2:
.space 4
label_37_v11:
.space 4
label_41_v15:
.space 4
label_22_v6:
.space 4
label_53_v19:
.space 4
label_51_v21:
.space 4
label_60_v25:
.space 4
label_35_v13:
.space 4
label_18_v4:
.space 4
# Allocated labels for virtual registers
label_87_v39:
.space 4
label_67_v28:
.space 4
label_74_v32:
.space 4
label_75_v33:
.space 4
label_77_v31:
.space 4
label_85_v38:
.space 4
label_70_v30:
.space 4
label_80_v35:
.space 4
label_90_v41:
.space 4
label_78_v34:
.space 4
label_83_v37:
.space 4
label_89_v40:
.space 4
label_82_v36:
.space 4
label_68_v29:
.space 4

.text
init_data:
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
# Original instruction: li v0,7
li $t5,7
la $t0,label_24_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,8
addiu $t5,$fp,8
la $t0,label_12_v1
sw $t5,0($t0)
# Original instruction: lw v2,0(v1)
la $t5,label_12_v1
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_14_v2
sw $t4,0($t0)
# Original instruction: addiu v3,v2,0
la $t5,label_14_v2
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_21_v3
sw $t4,0($t0)
# Original instruction: li v4,0
li $t5,0
la $t0,label_18_v4
sw $t5,0($t0)
# Original instruction: li v5,4
li $t5,4
la $t0,label_19_v5
sw $t5,0($t0)
# Original instruction: mul v6,v4,v5
la $t5,label_18_v4
lw $t5,0($t5)
la $t4,label_19_v5
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_22_v6
sw $t3,0($t0)
# Original instruction: add v7,v3,v6
la $t5,label_21_v3
lw $t5,0($t5)
la $t4,label_22_v6
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_25_v7
sw $t3,0($t0)
# Original instruction: sw v0,0(v7)
la $t5,label_24_v0
lw $t5,0($t5)
la $t4,label_25_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v8,0
li $t5,0
la $t0,label_40_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$fp,8
addiu $t5,$fp,8
la $t0,label_28_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_28_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_30_v10
sw $t4,0($t0)
# Original instruction: addiu v11,v10,0
la $t5,label_30_v10
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_37_v11
sw $t4,0($t0)
# Original instruction: li v12,1
li $t5,1
la $t0,label_34_v12
sw $t5,0($t0)
# Original instruction: li v13,4
li $t5,4
la $t0,label_35_v13
sw $t5,0($t0)
# Original instruction: mul v14,v12,v13
la $t5,label_34_v12
lw $t5,0($t5)
la $t4,label_35_v13
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_38_v14
sw $t3,0($t0)
# Original instruction: add v15,v11,v14
la $t5,label_37_v11
lw $t5,0($t5)
la $t4,label_38_v14
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_41_v15
sw $t3,0($t0)
# Original instruction: sw v8,0(v15)
la $t5,label_40_v8
lw $t5,0($t5)
la $t4,label_41_v15
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v16,14
li $t5,14
la $t0,label_56_v16
sw $t5,0($t0)
# Original instruction: addiu v17,$fp,8
addiu $t5,$fp,8
la $t0,label_44_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_44_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_46_v18
sw $t4,0($t0)
# Original instruction: addiu v19,v18,0
la $t5,label_46_v18
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_53_v19
sw $t4,0($t0)
# Original instruction: li v20,2
li $t5,2
la $t0,label_50_v20
sw $t5,0($t0)
# Original instruction: li v21,4
li $t5,4
la $t0,label_51_v21
sw $t5,0($t0)
# Original instruction: mul v22,v20,v21
la $t5,label_50_v20
lw $t5,0($t5)
la $t4,label_51_v21
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_54_v22
sw $t3,0($t0)
# Original instruction: add v23,v19,v22
la $t5,label_53_v19
lw $t5,0($t5)
la $t4,label_54_v22
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_57_v23
sw $t3,0($t0)
# Original instruction: sw v16,0(v23)
la $t5,label_56_v16
lw $t5,0($t5)
la $t4,label_57_v23
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v24,90
li $t5,90
la $t0,label_64_v24
sw $t5,0($t0)
# Original instruction: addiu v25,$fp,8
addiu $t5,$fp,8
la $t0,label_60_v25
sw $t5,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_60_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_62_v26
sw $t4,0($t0)
# Original instruction: addiu v27,v26,12
la $t5,label_62_v26
lw $t5,0($t5)
addiu $t4,$t5,12
la $t0,label_65_v27
sw $t4,0($t0)
# Original instruction: sb v24,0(v27)
la $t5,label_64_v24
lw $t5,0($t5)
la $t4,label_65_v27
lw $t4,0($t4)
sb $t5,0($t4)
label_7_return_init_data:
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
# Original instruction: addiu $sp,$sp,-24
addiu $sp,$sp,-24
# Original instruction: addiu v28,$fp,-24
addiu $t5,$fp,-24
la $t0,label_67_v28
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v28,0($sp)
la $t5,label_67_v28
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal init_data
jal init_data
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v29,$v0,$zero
add $t5,$v0,$zero
la $t0,label_68_v29
sw $t5,0($t0)
# Original instruction: addiu v30,$fp,-24
addiu $t5,$fp,-24
la $t0,label_70_v30
sw $t5,0($t0)
# Original instruction: addiu v31,v30,0
la $t5,label_70_v30
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_77_v31
sw $t4,0($t0)
# Original instruction: li v32,1
li $t5,1
la $t0,label_74_v32
sw $t5,0($t0)
# Original instruction: li v33,4
li $t5,4
la $t0,label_75_v33
sw $t5,0($t0)
# Original instruction: mul v34,v32,v33
la $t5,label_74_v32
lw $t5,0($t5)
la $t4,label_75_v33
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_78_v34
sw $t3,0($t0)
# Original instruction: add v35,v31,v34
la $t5,label_77_v31
lw $t5,0($t5)
la $t4,label_78_v34
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_80_v35
sw $t3,0($t0)
# Original instruction: lw v36,0(v35)
la $t5,label_80_v35
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_82_v36
sw $t4,0($t0)
# Original instruction: add $a0,v36,$zero
la $t5,label_82_v36
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v37,$zero,$zero
add $t5,$zero,$zero
la $t0,label_83_v37
sw $t5,0($t0)
# Original instruction: addiu v38,$fp,-24
addiu $t5,$fp,-24
la $t0,label_85_v38
sw $t5,0($t0)
# Original instruction: addiu v39,v38,12
la $t5,label_85_v38
lw $t5,0($t5)
addiu $t4,$t5,12
la $t0,label_87_v39
sw $t4,0($t0)
# Original instruction: lb v40,0(v39)
la $t5,label_87_v39
lw $t5,0($t5)
lb $t4,0($t5)
la $t0,label_89_v40
sw $t4,0($t0)
# Original instruction: add $a0,v40,$zero
la $t5,label_89_v40
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,11
li $v0,11
# Original instruction: syscall
syscall
# Original instruction: add v41,$zero,$zero
add $t5,$zero,$zero
la $t0,label_90_v41
sw $t5,0($t0)
label_9_return_main:
# Original instruction: addiu $sp,$sp,24
addiu $sp,$sp,24
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

