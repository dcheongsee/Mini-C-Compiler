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
label_70_v24:
.space 4
.align 2
label_22_v0:
.space 4
.align 2
label_38_v8:
.space 4
.align 2
label_68_v30:
.space 4
.align 2
label_60_v26:
.space 4
.align 2
label_67_v27:
.space 4
.align 2
label_49_v21:
.space 4
.align 2
label_48_v20:
.space 4
.align 2
label_35_v11:
.space 4
.align 2
label_20_v6:
.space 4
.align 2
label_28_v10:
.space 4
.align 2
label_52_v22:
.space 4
.align 2
label_65_v29:
.space 4
.align 2
label_42_v17:
.space 4
.align 2
label_58_v25:
.space 4
.align 2
label_54_v16:
.space 4
.align 2
label_12_v2:
.space 4
.align 2
label_26_v9:
.space 4
.align 2
label_64_v28:
.space 4
.align 2
label_71_v31:
.space 4
.align 2
label_51_v19:
.space 4
.align 2
label_36_v14:
.space 4
.align 2
label_16_v4:
.space 4
.align 2
label_10_v1:
.space 4
.align 2
label_17_v5:
.space 4
.align 2
label_39_v15:
.space 4
.align 2
label_32_v12:
.space 4
.align 2
label_19_v3:
.space 4
.align 2
label_44_v18:
.space 4
.align 2
label_55_v23:
.space 4
.align 2
label_33_v13:
.space 4
.align 2
label_23_v7:
.space 4
# Allocated labels for virtual registers
.align 2
label_80_v36:
.space 4
.align 2
label_86_v39:
.space 4
.align 2
label_76_v34:
.space 4
.align 2
label_73_v32:
.space 4
.align 2
label_84_v38:
.space 4
.align 2
label_74_v33:
.space 4
.align 2
label_81_v37:
.space 4
.align 2
label_88_v40:
.space 4
.align 2
label_83_v35:
.space 4
.align 2
label_89_v41:
.space 4

.text
fill_bag:
# Original instruction: andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
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
# Original instruction: li v0,100
li $t5,100
la $t0,label_22_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,8
addiu $t5,$fp,8
la $t0,label_10_v1
sw $t5,0($t0)
# Original instruction: lw v2,0(v1)
la $t5,label_10_v1
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_12_v2
sw $t4,0($t0)
# Original instruction: addiu v3,v2,0
la $t5,label_12_v2
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_19_v3
sw $t4,0($t0)
# Original instruction: li v4,0
li $t5,0
la $t0,label_16_v4
sw $t5,0($t0)
# Original instruction: li v5,4
li $t5,4
la $t0,label_17_v5
sw $t5,0($t0)
# Original instruction: mul v6,v4,v5
la $t5,label_16_v4
lw $t5,0($t5)
la $t4,label_17_v5
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_20_v6
sw $t3,0($t0)
# Original instruction: add v7,v3,v6
la $t5,label_19_v3
lw $t5,0($t5)
la $t4,label_20_v6
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_23_v7
sw $t3,0($t0)
# Original instruction: sw v0,0(v7)
la $t5,label_22_v0
lw $t5,0($t5)
la $t4,label_23_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v8,200
li $t5,200
la $t0,label_38_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$fp,8
addiu $t5,$fp,8
la $t0,label_26_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_26_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_28_v10
sw $t4,0($t0)
# Original instruction: addiu v11,v10,0
la $t5,label_28_v10
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_35_v11
sw $t4,0($t0)
# Original instruction: li v12,1
li $t5,1
la $t0,label_32_v12
sw $t5,0($t0)
# Original instruction: li v13,4
li $t5,4
la $t0,label_33_v13
sw $t5,0($t0)
# Original instruction: mul v14,v12,v13
la $t5,label_32_v12
lw $t5,0($t5)
la $t4,label_33_v13
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_36_v14
sw $t3,0($t0)
# Original instruction: add v15,v11,v14
la $t5,label_35_v11
lw $t5,0($t5)
la $t4,label_36_v14
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_39_v15
sw $t3,0($t0)
# Original instruction: sw v8,0(v15)
la $t5,label_38_v8
lw $t5,0($t5)
la $t4,label_39_v15
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v16,300
li $t5,300
la $t0,label_54_v16
sw $t5,0($t0)
# Original instruction: addiu v17,$fp,8
addiu $t5,$fp,8
la $t0,label_42_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_42_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_44_v18
sw $t4,0($t0)
# Original instruction: addiu v19,v18,0
la $t5,label_44_v18
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_51_v19
sw $t4,0($t0)
# Original instruction: li v20,2
li $t5,2
la $t0,label_48_v20
sw $t5,0($t0)
# Original instruction: li v21,4
li $t5,4
la $t0,label_49_v21
sw $t5,0($t0)
# Original instruction: mul v22,v20,v21
la $t5,label_48_v20
lw $t5,0($t5)
la $t4,label_49_v21
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_52_v22
sw $t3,0($t0)
# Original instruction: add v23,v19,v22
la $t5,label_51_v19
lw $t5,0($t5)
la $t4,label_52_v22
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_55_v23
sw $t3,0($t0)
# Original instruction: sw v16,0(v23)
la $t5,label_54_v16
lw $t5,0($t5)
la $t4,label_55_v23
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v24,400
li $t5,400
la $t0,label_70_v24
sw $t5,0($t0)
# Original instruction: addiu v25,$fp,8
addiu $t5,$fp,8
la $t0,label_58_v25
sw $t5,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_58_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_60_v26
sw $t4,0($t0)
# Original instruction: addiu v27,v26,0
la $t5,label_60_v26
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_67_v27
sw $t4,0($t0)
# Original instruction: li v28,3
li $t5,3
la $t0,label_64_v28
sw $t5,0($t0)
# Original instruction: li v29,4
li $t5,4
la $t0,label_65_v29
sw $t5,0($t0)
# Original instruction: mul v30,v28,v29
la $t5,label_64_v28
lw $t5,0($t5)
la $t4,label_65_v29
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_68_v30
sw $t3,0($t0)
# Original instruction: add v31,v27,v30
la $t5,label_67_v27
lw $t5,0($t5)
la $t4,label_68_v30
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_71_v31
sw $t3,0($t0)
# Original instruction: sw v24,0(v31)
la $t5,label_70_v24
lw $t5,0($t5)
la $t4,label_71_v31
lw $t4,0($t4)
sw $t5,0($t4)
label_5_return_fill_bag:
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
# Original instruction: andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
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
# Original instruction: addiu v32,$fp,-24
addiu $t5,$fp,-24
la $t0,label_73_v32
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v32,0($sp)
la $t5,label_73_v32
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal fill_bag
jal fill_bag
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v33,$v0,$zero
add $t5,$v0,$zero
la $t0,label_74_v33
sw $t5,0($t0)
# Original instruction: addiu v34,$fp,-24
addiu $t5,$fp,-24
la $t0,label_76_v34
sw $t5,0($t0)
# Original instruction: addiu v35,v34,0
la $t5,label_76_v34
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_83_v35
sw $t4,0($t0)
# Original instruction: li v36,2
li $t5,2
la $t0,label_80_v36
sw $t5,0($t0)
# Original instruction: li v37,4
li $t5,4
la $t0,label_81_v37
sw $t5,0($t0)
# Original instruction: mul v38,v36,v37
la $t5,label_80_v36
lw $t5,0($t5)
la $t4,label_81_v37
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_84_v38
sw $t3,0($t0)
# Original instruction: add v39,v35,v38
la $t5,label_83_v35
lw $t5,0($t5)
la $t4,label_84_v38
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_86_v39
sw $t3,0($t0)
# Original instruction: lw v40,0(v39)
la $t5,label_86_v39
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_88_v40
sw $t4,0($t0)
# Original instruction: add $a0,v40,$zero
la $t5,label_88_v40
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v41,$zero,$zero
add $t5,$zero,$zero
la $t0,label_89_v41
sw $t5,0($t0)
label_7_return_main:
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

