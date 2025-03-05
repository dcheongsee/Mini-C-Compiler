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
label_112_v55:
.space 4
.align 2
label_101_v49:
.space 4
.align 2
label_11_v3:
.space 4
.align 2
label_104_v48:
.space 4
.align 2
label_28_v6:
.space 4
.align 2
label_16_v0:
.space 4
.align 2
label_23_v9:
.space 4
.align 2
label_78_v34:
.space 4
.align 2
label_96_v46:
.space 4
.align 2
label_62_v28:
.space 4
.align 2
label_63_v29:
.space 4
.align 2
label_84_v40:
.space 4
.align 2
label_79_v37:
.space 4
.align 2
label_40_v12:
.space 4
.align 2
label_57_v25:
.space 4
.align 2
label_65_v27:
.space 4
.align 2
label_14_v4:
.space 4
.align 2
label_68_v31:
.space 4
.align 2
label_92_v44:
.space 4
.align 2
label_45_v19:
.space 4
.align 2
label_26_v10:
.space 4
.align 2
label_94_v45:
.space 4
.align 2
label_46_v20:
.space 4
.align 2
label_105_v51:
.space 4
.align 2
label_66_v30:
.space 4
.align 2
label_71_v33:
.space 4
.align 2
label_110_v54:
.space 4
.align 2
label_70_v32:
.space 4
.align 2
label_13_v1:
.space 4
.align 2
label_107_v52:
.space 4
.align 2
label_29_v11:
.space 4
.align 2
label_37_v13:
.space 4
.align 2
label_109_v53:
.space 4
.align 2
label_55_v24:
.space 4
.align 2
label_52_v18:
.space 4
.align 2
label_76_v36:
.space 4
.align 2
label_38_v16:
.space 4
.align 2
label_83_v39:
.space 4
.align 2
label_17_v5:
.space 4
.align 2
label_50_v22:
.space 4
.align 2
label_53_v23:
.space 4
.align 2
label_89_v43:
.space 4
.align 2
label_34_v14:
.space 4
.align 2
label_35_v15:
.space 4
.align 2
label_58_v26:
.space 4
.align 2
label_91_v41:
.space 4
.align 2
label_25_v7:
.space 4
.align 2
label_22_v8:
.space 4
.align 2
label_41_v17:
.space 4
.align 2
label_49_v21:
.space 4
.align 2
label_97_v47:
.space 4
.align 2
label_88_v42:
.space 4
.align 2
label_75_v35:
.space 4
.align 2
label_81_v38:
.space 4
.align 2
label_102_v50:
.space 4
.align 2
label_10_v2:
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
# Original instruction: li v3,4
li $t5,4
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
# Original instruction: li v9,4
li $t5,4
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
# Original instruction: li v15,4
li $t5,4
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
la $t0,label_52_v18
sw $t5,0($t0)
# Original instruction: li v19,0
li $t5,0
la $t0,label_45_v19
sw $t5,0($t0)
# Original instruction: li v20,1
li $t5,1
la $t0,label_46_v20
sw $t5,0($t0)
# Original instruction: sub v21,v19,v20
la $t5,label_45_v19
lw $t5,0($t5)
la $t4,label_46_v20
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_49_v21
sw $t3,0($t0)
# Original instruction: li v22,4
li $t5,4
la $t0,label_50_v22
sw $t5,0($t0)
# Original instruction: mul v23,v21,v22
la $t5,label_49_v21
lw $t5,0($t5)
la $t4,label_50_v22
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_53_v23
sw $t3,0($t0)
# Original instruction: add v24,v18,v23
la $t5,label_52_v18
lw $t5,0($t5)
la $t4,label_53_v23
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_55_v24
sw $t3,0($t0)
# Original instruction: lw v25,0(v24)
la $t5,label_55_v24
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_57_v25
sw $t4,0($t0)
# Original instruction: add $a0,v25,$zero
la $t5,label_57_v25
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v26,$zero,$zero
add $t5,$zero,$zero
la $t0,label_58_v26
sw $t5,0($t0)
# Original instruction: addiu v27,$fp,-20
addiu $t5,$fp,-20
la $t0,label_65_v27
sw $t5,0($t0)
# Original instruction: li v28,0
li $t5,0
la $t0,label_62_v28
sw $t5,0($t0)
# Original instruction: li v29,4
li $t5,4
la $t0,label_63_v29
sw $t5,0($t0)
# Original instruction: mul v30,v28,v29
la $t5,label_62_v28
lw $t5,0($t5)
la $t4,label_63_v29
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_66_v30
sw $t3,0($t0)
# Original instruction: add v31,v27,v30
la $t5,label_65_v27
lw $t5,0($t5)
la $t4,label_66_v30
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_68_v31
sw $t3,0($t0)
# Original instruction: lw v32,0(v31)
la $t5,label_68_v31
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_70_v32
sw $t4,0($t0)
# Original instruction: add $a0,v32,$zero
la $t5,label_70_v32
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v33,$zero,$zero
add $t5,$zero,$zero
la $t0,label_71_v33
sw $t5,0($t0)
# Original instruction: addiu v34,$fp,-20
addiu $t5,$fp,-20
la $t0,label_78_v34
sw $t5,0($t0)
# Original instruction: li v35,1
li $t5,1
la $t0,label_75_v35
sw $t5,0($t0)
# Original instruction: li v36,4
li $t5,4
la $t0,label_76_v36
sw $t5,0($t0)
# Original instruction: mul v37,v35,v36
la $t5,label_75_v35
lw $t5,0($t5)
la $t4,label_76_v36
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_79_v37
sw $t3,0($t0)
# Original instruction: add v38,v34,v37
la $t5,label_78_v34
lw $t5,0($t5)
la $t4,label_79_v37
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_81_v38
sw $t3,0($t0)
# Original instruction: lw v39,0(v38)
la $t5,label_81_v38
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_83_v39
sw $t4,0($t0)
# Original instruction: add $a0,v39,$zero
la $t5,label_83_v39
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v40,$zero,$zero
add $t5,$zero,$zero
la $t0,label_84_v40
sw $t5,0($t0)
# Original instruction: addiu v41,$fp,-20
addiu $t5,$fp,-20
la $t0,label_91_v41
sw $t5,0($t0)
# Original instruction: li v42,2
li $t5,2
la $t0,label_88_v42
sw $t5,0($t0)
# Original instruction: li v43,4
li $t5,4
la $t0,label_89_v43
sw $t5,0($t0)
# Original instruction: mul v44,v42,v43
la $t5,label_88_v42
lw $t5,0($t5)
la $t4,label_89_v43
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_92_v44
sw $t3,0($t0)
# Original instruction: add v45,v41,v44
la $t5,label_91_v41
lw $t5,0($t5)
la $t4,label_92_v44
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_94_v45
sw $t3,0($t0)
# Original instruction: lw v46,0(v45)
la $t5,label_94_v45
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_96_v46
sw $t4,0($t0)
# Original instruction: add $a0,v46,$zero
la $t5,label_96_v46
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v47,$zero,$zero
add $t5,$zero,$zero
la $t0,label_97_v47
sw $t5,0($t0)
# Original instruction: addiu v48,$fp,-20
addiu $t5,$fp,-20
la $t0,label_104_v48
sw $t5,0($t0)
# Original instruction: li v49,3
li $t5,3
la $t0,label_101_v49
sw $t5,0($t0)
# Original instruction: li v50,4
li $t5,4
la $t0,label_102_v50
sw $t5,0($t0)
# Original instruction: mul v51,v49,v50
la $t5,label_101_v49
lw $t5,0($t5)
la $t4,label_102_v50
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_105_v51
sw $t3,0($t0)
# Original instruction: add v52,v48,v51
la $t5,label_104_v48
lw $t5,0($t5)
la $t4,label_105_v51
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_107_v52
sw $t3,0($t0)
# Original instruction: lw v53,0(v52)
la $t5,label_107_v52
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_109_v53
sw $t4,0($t0)
# Original instruction: add $a0,v53,$zero
la $t5,label_109_v53
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v54,$zero,$zero
add $t5,$zero,$zero
la $t0,label_110_v54
sw $t5,0($t0)
# Original instruction: li v55,0
li $t5,0
la $t0,label_112_v55
sw $t5,0($t0)
# Original instruction: add $v0,v55,$zero
la $t5,label_112_v55
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

