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
label_103_v47:
.space 4
.align 2
label_93_v41:
.space 4
.align 2
label_92_v35:
.space 4
.align 2
label_79_v33:
.space 4
.align 2
label_115_v53:
.space 4
.align 2
label_146_v69:
.space 4
.align 2
label_137_v64:
.space 4
.align 2
label_51_v19:
.space 4
.align 2
label_99_v44:
.space 4
.align 2
label_75_v31:
.space 4
.align 2
label_95_v42:
.space 4
.align 2
label_38_v13:
.space 4
.align 2
label_141_v66:
.space 4
.align 2
label_131_v61:
.space 4
.align 2
label_39_v14:
.space 4
.align 2
label_64_v25:
.space 4
.align 2
label_112_v51:
.space 4
.align 2
label_105_v48:
.space 4
.align 2
label_140_v62:
.space 4
.align 2
label_127_v59:
.space 4
.align 2
label_58_v22:
.space 4
.align 2
label_44_v10:
.space 4
.align 2
label_35_v12:
.space 4
.align 2
label_45_v16:
.space 4
.align 2
label_70_v29:
.space 4
.align 2
label_130_v60:
.space 4
.align 2
label_22_v4:
.space 4
.align 2
label_119_v54:
.space 4
.align 2
label_73_v30:
.space 4
.align 2
label_118_v55:
.space 4
.align 2
label_23_v5:
.space 4
.align 2
label_83_v37:
.space 4
.align 2
label_42_v15:
.space 4
.align 2
label_50_v18:
.space 4
.align 2
label_90_v40:
.space 4
.align 2
label_47_v17:
.space 4
.align 2
label_86_v38:
.space 4
.align 2
label_145_v68:
.space 4
.align 2
label_143_v67:
.space 4
.align 2
label_126_v58:
.space 4
.align 2
label_98_v43:
.space 4
.align 2
label_123_v57:
.space 4
.align 2
label_87_v39:
.space 4
.align 2
label_31_v9:
.space 4
.align 2
label_19_v3:
.space 4
.align 2
label_55_v21:
.space 4
.align 2
label_15_v1:
.space 4
.align 2
label_61_v24:
.space 4
.align 2
label_134_v63:
.space 4
.align 2
label_108_v49:
.space 4
.align 2
label_121_v56:
.space 4
.align 2
label_28_v7:
.space 4
.align 2
label_29_v8:
.space 4
.align 2
label_71_v28:
.space 4
.align 2
label_14_v0:
.space 4
.align 2
label_113_v52:
.space 4
.align 2
label_41_v11:
.space 4
.align 2
label_65_v26:
.space 4
.align 2
label_18_v2:
.space 4
.align 2
label_25_v6:
.space 4
.align 2
label_78_v32:
.space 4
.align 2
label_109_v50:
.space 4
.align 2
label_67_v27:
.space 4
.align 2
label_138_v65:
.space 4
.align 2
label_102_v46:
.space 4
.align 2
label_54_v20:
.space 4
.align 2
label_59_v23:
.space 4
.align 2
label_89_v36:
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
# Original instruction: addiu $sp,$sp,-148
addiu $sp,$sp,-148
# Original instruction: la v0,str_2355908448
la $t5,str_2355908448
la $t0,label_14_v0
sw $t5,0($t0)
# Original instruction: add $a0,v0,$zero
la $t5,label_14_v0
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v1,$zero,$zero
add $t5,$zero,$zero
la $t0,label_15_v1
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v2,$v0,$zero
add $t5,$v0,$zero
la $t0,label_18_v2
sw $t5,0($t0)
# Original instruction: addiu v3,$fp,-12
addiu $t5,$fp,-12
la $t0,label_19_v3
sw $t5,0($t0)
# Original instruction: sw v2,0(v3)
la $t5,label_18_v2
lw $t5,0($t5)
la $t4,label_19_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v4,0
li $t5,0
la $t0,label_22_v4
sw $t5,0($t0)
# Original instruction: addiu v5,$fp,-20
addiu $t5,$fp,-20
la $t0,label_23_v5
sw $t5,0($t0)
# Original instruction: sw v4,0(v5)
la $t5,label_22_v4
lw $t5,0($t5)
la $t4,label_23_v5
lw $t4,0($t4)
sw $t5,0($t4)
label_7_while_start:
# Original instruction: addiu v6,$fp,-20
addiu $t5,$fp,-20
la $t0,label_25_v6
sw $t5,0($t0)
# Original instruction: lw v7,0(v6)
la $t5,label_25_v6
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_28_v7
sw $t4,0($t0)
# Original instruction: li v8,32
li $t5,32
la $t0,label_29_v8
sw $t5,0($t0)
# Original instruction: slt v9,v7,v8
la $t5,label_28_v7
lw $t5,0($t5)
la $t4,label_29_v8
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_31_v9
sw $t3,0($t0)
# Original instruction: beqz v9,label_8_while_exit
la $t5,label_31_v9
lw $t5,0($t5)
beqz $t5,label_8_while_exit
# Original instruction: li v10,0
li $t5,0
la $t0,label_44_v10
sw $t5,0($t0)
# Original instruction: addiu v11,$fp,-148
addiu $t5,$fp,-148
la $t0,label_41_v11
sw $t5,0($t0)
# Original instruction: addiu v12,$fp,-20
addiu $t5,$fp,-20
la $t0,label_35_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_35_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_38_v13
sw $t4,0($t0)
# Original instruction: li v14,4
li $t5,4
la $t0,label_39_v14
sw $t5,0($t0)
# Original instruction: mul v15,v13,v14
la $t5,label_38_v13
lw $t5,0($t5)
la $t4,label_39_v14
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_42_v15
sw $t3,0($t0)
# Original instruction: add v16,v11,v15
la $t5,label_41_v11
lw $t5,0($t5)
la $t4,label_42_v15
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_45_v16
sw $t3,0($t0)
# Original instruction: sw v10,0(v16)
la $t5,label_44_v10
lw $t5,0($t5)
la $t4,label_45_v16
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v17,$fp,-20
addiu $t5,$fp,-20
la $t0,label_47_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_47_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_50_v18
sw $t4,0($t0)
# Original instruction: li v19,1
li $t5,1
la $t0,label_51_v19
sw $t5,0($t0)
# Original instruction: add v20,v18,v19
la $t5,label_50_v18
lw $t5,0($t5)
la $t4,label_51_v19
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_54_v20
sw $t3,0($t0)
# Original instruction: addiu v21,$fp,-20
addiu $t5,$fp,-20
la $t0,label_55_v21
sw $t5,0($t0)
# Original instruction: sw v20,0(v21)
la $t5,label_54_v20
lw $t5,0($t5)
la $t4,label_55_v21
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_7_while_start
j label_7_while_start
label_8_while_exit:
# Original instruction: li v22,0
li $t5,0
la $t0,label_58_v22
sw $t5,0($t0)
# Original instruction: addiu v23,$fp,-20
addiu $t5,$fp,-20
la $t0,label_59_v23
sw $t5,0($t0)
# Original instruction: sw v22,0(v23)
la $t5,label_58_v22
lw $t5,0($t5)
la $t4,label_59_v23
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v24,$fp,-12
addiu $t5,$fp,-12
la $t0,label_61_v24
sw $t5,0($t0)
# Original instruction: lw v25,0(v24)
la $t5,label_61_v24
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_64_v25
sw $t4,0($t0)
# Original instruction: addiu v26,$fp,-16
addiu $t5,$fp,-16
la $t0,label_65_v26
sw $t5,0($t0)
# Original instruction: sw v25,0(v26)
la $t5,label_64_v25
lw $t5,0($t5)
la $t4,label_65_v26
lw $t4,0($t4)
sw $t5,0($t4)
label_9_while_start:
# Original instruction: addiu v27,$fp,-16
addiu $t5,$fp,-16
la $t0,label_67_v27
sw $t5,0($t0)
# Original instruction: lw v28,0(v27)
la $t5,label_67_v27
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_71_v28
sw $t4,0($t0)
# Original instruction: li v29,0
li $t5,0
la $t0,label_70_v29
sw $t5,0($t0)
# Original instruction: slt v30,v29,v28
la $t5,label_70_v29
lw $t5,0($t5)
la $t4,label_71_v28
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_73_v30
sw $t3,0($t0)
# Original instruction: beqz v30,label_10_while_exit
la $t5,label_73_v30
lw $t5,0($t5)
beqz $t5,label_10_while_exit
# Original instruction: addiu v31,$fp,-16
addiu $t5,$fp,-16
la $t0,label_75_v31
sw $t5,0($t0)
# Original instruction: lw v32,0(v31)
la $t5,label_75_v31
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_78_v32
sw $t4,0($t0)
# Original instruction: li v33,2
li $t5,2
la $t0,label_79_v33
sw $t5,0($t0)
# Original instruction: div v32,v33
la $t5,label_78_v32
lw $t5,0($t5)
la $t4,label_79_v33
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mfhi v35
mfhi $t5
la $t0,label_92_v35
sw $t5,0($t0)
# Original instruction: addiu v36,$fp,-148
addiu $t5,$fp,-148
la $t0,label_89_v36
sw $t5,0($t0)
# Original instruction: addiu v37,$fp,-20
addiu $t5,$fp,-20
la $t0,label_83_v37
sw $t5,0($t0)
# Original instruction: lw v38,0(v37)
la $t5,label_83_v37
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_86_v38
sw $t4,0($t0)
# Original instruction: li v39,4
li $t5,4
la $t0,label_87_v39
sw $t5,0($t0)
# Original instruction: mul v40,v38,v39
la $t5,label_86_v38
lw $t5,0($t5)
la $t4,label_87_v39
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_90_v40
sw $t3,0($t0)
# Original instruction: add v41,v36,v40
la $t5,label_89_v36
lw $t5,0($t5)
la $t4,label_90_v40
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_93_v41
sw $t3,0($t0)
# Original instruction: sw v35,0(v41)
la $t5,label_92_v35
lw $t5,0($t5)
la $t4,label_93_v41
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v42,$fp,-16
addiu $t5,$fp,-16
la $t0,label_95_v42
sw $t5,0($t0)
# Original instruction: lw v43,0(v42)
la $t5,label_95_v42
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_98_v43
sw $t4,0($t0)
# Original instruction: li v44,2
li $t5,2
la $t0,label_99_v44
sw $t5,0($t0)
# Original instruction: div v43,v44
la $t5,label_98_v43
lw $t5,0($t5)
la $t4,label_99_v44
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mflo v46
mflo $t5
la $t0,label_102_v46
sw $t5,0($t0)
# Original instruction: addiu v47,$fp,-16
addiu $t5,$fp,-16
la $t0,label_103_v47
sw $t5,0($t0)
# Original instruction: sw v46,0(v47)
la $t5,label_102_v46
lw $t5,0($t5)
la $t4,label_103_v47
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v48,$fp,-20
addiu $t5,$fp,-20
la $t0,label_105_v48
sw $t5,0($t0)
# Original instruction: lw v49,0(v48)
la $t5,label_105_v48
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_108_v49
sw $t4,0($t0)
# Original instruction: li v50,1
li $t5,1
la $t0,label_109_v50
sw $t5,0($t0)
# Original instruction: add v51,v49,v50
la $t5,label_108_v49
lw $t5,0($t5)
la $t4,label_109_v50
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_112_v51
sw $t3,0($t0)
# Original instruction: addiu v52,$fp,-20
addiu $t5,$fp,-20
la $t0,label_113_v52
sw $t5,0($t0)
# Original instruction: sw v51,0(v52)
la $t5,label_112_v51
lw $t5,0($t5)
la $t4,label_113_v52
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_9_while_start
j label_9_while_start
label_10_while_exit:
label_11_while_start:
# Original instruction: addiu v53,$fp,-20
addiu $t5,$fp,-20
la $t0,label_115_v53
sw $t5,0($t0)
# Original instruction: lw v54,0(v53)
la $t5,label_115_v53
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_119_v54
sw $t4,0($t0)
# Original instruction: li v55,0
li $t5,0
la $t0,label_118_v55
sw $t5,0($t0)
# Original instruction: slt v56,v55,v54
la $t5,label_118_v55
lw $t5,0($t5)
la $t4,label_119_v54
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_121_v56
sw $t3,0($t0)
# Original instruction: beqz v56,label_12_while_exit
la $t5,label_121_v56
lw $t5,0($t5)
beqz $t5,label_12_while_exit
# Original instruction: addiu v57,$fp,-20
addiu $t5,$fp,-20
la $t0,label_123_v57
sw $t5,0($t0)
# Original instruction: lw v58,0(v57)
la $t5,label_123_v57
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_126_v58
sw $t4,0($t0)
# Original instruction: li v59,1
li $t5,1
la $t0,label_127_v59
sw $t5,0($t0)
# Original instruction: sub v60,v58,v59
la $t5,label_126_v58
lw $t5,0($t5)
la $t4,label_127_v59
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_130_v60
sw $t3,0($t0)
# Original instruction: addiu v61,$fp,-20
addiu $t5,$fp,-20
la $t0,label_131_v61
sw $t5,0($t0)
# Original instruction: sw v60,0(v61)
la $t5,label_130_v60
lw $t5,0($t5)
la $t4,label_131_v61
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v62,$fp,-148
addiu $t5,$fp,-148
la $t0,label_140_v62
sw $t5,0($t0)
# Original instruction: addiu v63,$fp,-20
addiu $t5,$fp,-20
la $t0,label_134_v63
sw $t5,0($t0)
# Original instruction: lw v64,0(v63)
la $t5,label_134_v63
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_137_v64
sw $t4,0($t0)
# Original instruction: li v65,4
li $t5,4
la $t0,label_138_v65
sw $t5,0($t0)
# Original instruction: mul v66,v64,v65
la $t5,label_137_v64
lw $t5,0($t5)
la $t4,label_138_v65
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_141_v66
sw $t3,0($t0)
# Original instruction: add v67,v62,v66
la $t5,label_140_v62
lw $t5,0($t5)
la $t4,label_141_v66
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_143_v67
sw $t3,0($t0)
# Original instruction: lw v68,0(v67)
la $t5,label_143_v67
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_145_v68
sw $t4,0($t0)
# Original instruction: add $a0,v68,$zero
la $t5,label_145_v68
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v69,$zero,$zero
add $t5,$zero,$zero
la $t0,label_146_v69
sw $t5,0($t0)
# Original instruction: j label_11_while_start
j label_11_while_start
label_12_while_exit:
label_5_return_main:
# Original instruction: addiu $sp,$sp,148
addiu $sp,$sp,148
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

