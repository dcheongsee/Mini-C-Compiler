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
label_46_v10:
.space 4
label_41_v17:
.space 4
label_15_v1:
.space 4
label_13_v3:
.space 4
label_20_v6:
.space 4
label_23_v5:
.space 4
label_12_v2:
.space 4
label_44_v18:
.space 4
label_24_v8:
.space 4
label_47_v19:
.space 4
label_27_v9:
.space 4
label_35_v11:
.space 4
label_40_v16:
.space 4
label_36_v14:
.space 4
label_26_v0:
.space 4
label_43_v15:
.space 4
label_33_v13:
.space 4
label_16_v4:
.space 4
label_32_v12:
.space 4
label_21_v7:
.space 4
# Allocated labels for virtual registers
label_139_v67:
.space 4
label_127_v61:
.space 4
label_128_v62:
.space 4
label_123_v59:
.space 4
label_59_v23:
.space 4
label_65_v29:
.space 4
label_96_v40:
.space 4
label_68_v30:
.space 4
label_74_v33:
.space 4
label_72_v32:
.space 4
label_49_v20:
.space 4
label_120_v57:
.space 4
label_90_v42:
.space 4
label_89_v41:
.space 4
label_106_v50:
.space 4
label_101_v47:
.space 4
label_152_v74:
.space 4
label_138_v64:
.space 4
label_52_v22:
.space 4
label_69_v31:
.space 4
label_135_v65:
.space 4
label_57_v25:
.space 4
label_84_v36:
.space 4
label_102_v48:
.space 4
label_64_v28:
.space 4
label_148_v72:
.space 4
label_56_v24:
.space 4
label_114_v54:
.space 4
label_156_v76:
.space 4
label_130_v60:
.space 4
label_117_v53:
.space 4
label_165_v81:
.space 4
label_164_v80:
.space 4
label_109_v49:
.space 4
label_77_v35:
.space 4
label_157_v77:
.space 4
label_122_v58:
.space 4
label_94_v44:
.space 4
label_151_v71:
.space 4
label_82_v38:
.space 4
label_143_v69:
.space 4
label_99_v46:
.space 4
label_71_v27:
.space 4
label_110_v52:
.space 4
label_131_v63:
.space 4
label_51_v21:
.space 4
label_81_v37:
.space 4
label_159_v75:
.space 4
label_149_v73:
.space 4
label_144_v70:
.space 4
label_60_v26:
.space 4
label_118_v56:
.space 4
label_93_v43:
.space 4
label_136_v66:
.space 4
label_160_v78:
.space 4
label_107_v51:
.space 4
label_115_v55:
.space 4
label_141_v68:
.space 4
label_97_v45:
.space 4
label_85_v39:
.space 4
label_162_v79:
.space 4
label_76_v34:
.space 4

.text
fill_matrix:
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
la $t0,label_26_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,8
addiu $t5,$fp,8
la $t0,label_15_v1
sw $t5,0($t0)
# Original instruction: li v2,0
li $t5,0
la $t0,label_12_v2
sw $t5,0($t0)
# Original instruction: li v3,8
li $t5,8
la $t0,label_13_v3
sw $t5,0($t0)
# Original instruction: mul v4,v2,v3
la $t5,label_12_v2
lw $t5,0($t5)
la $t4,label_13_v3
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_16_v4
sw $t3,0($t0)
# Original instruction: add v5,v1,v4
la $t5,label_15_v1
lw $t5,0($t5)
la $t4,label_16_v4
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_23_v5
sw $t3,0($t0)
# Original instruction: li v6,0
li $t5,0
la $t0,label_20_v6
sw $t5,0($t0)
# Original instruction: li v7,4
li $t5,4
la $t0,label_21_v7
sw $t5,0($t0)
# Original instruction: mul v8,v6,v7
la $t5,label_20_v6
lw $t5,0($t5)
la $t4,label_21_v7
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_24_v8
sw $t3,0($t0)
# Original instruction: add v9,v5,v8
la $t5,label_23_v5
lw $t5,0($t5)
la $t4,label_24_v8
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_27_v9
sw $t3,0($t0)
# Original instruction: sw v0,0(v9)
la $t5,label_26_v0
lw $t5,0($t5)
la $t4,label_27_v9
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v10,3
li $t5,3
la $t0,label_46_v10
sw $t5,0($t0)
# Original instruction: addiu v11,$fp,8
addiu $t5,$fp,8
la $t0,label_35_v11
sw $t5,0($t0)
# Original instruction: li v12,0
li $t5,0
la $t0,label_32_v12
sw $t5,0($t0)
# Original instruction: li v13,8
li $t5,8
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
la $t0,label_43_v15
sw $t3,0($t0)
# Original instruction: li v16,1
li $t5,1
la $t0,label_40_v16
sw $t5,0($t0)
# Original instruction: li v17,4
li $t5,4
la $t0,label_41_v17
sw $t5,0($t0)
# Original instruction: mul v18,v16,v17
la $t5,label_40_v16
lw $t5,0($t5)
la $t4,label_41_v17
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_44_v18
sw $t3,0($t0)
# Original instruction: add v19,v15,v18
la $t5,label_43_v15
lw $t5,0($t5)
la $t4,label_44_v18
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_47_v19
sw $t3,0($t0)
# Original instruction: sw v10,0(v19)
la $t5,label_46_v10
lw $t5,0($t5)
la $t4,label_47_v19
lw $t4,0($t4)
sw $t5,0($t4)
label_5_return_fill_matrix:
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
# Original instruction: addiu $sp,$sp,-16
addiu $sp,$sp,-16
# Original instruction: addiu v20,$fp,-16
addiu $t5,$fp,-16
la $t0,label_49_v20
sw $t5,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_49_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_51_v21
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v21,0($sp)
la $t5,label_51_v21
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal fill_matrix
jal fill_matrix
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v22,$v0,$zero
add $t5,$v0,$zero
la $t0,label_52_v22
sw $t5,0($t0)
# Original instruction: addiu v23,$fp,-16
addiu $t5,$fp,-16
la $t0,label_59_v23
sw $t5,0($t0)
# Original instruction: li v24,0
li $t5,0
la $t0,label_56_v24
sw $t5,0($t0)
# Original instruction: li v25,8
li $t5,8
la $t0,label_57_v25
sw $t5,0($t0)
# Original instruction: mul v26,v24,v25
la $t5,label_56_v24
lw $t5,0($t5)
la $t4,label_57_v25
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_60_v26
sw $t3,0($t0)
# Original instruction: add v27,v23,v26
la $t5,label_59_v23
lw $t5,0($t5)
la $t4,label_60_v26
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_71_v27
sw $t3,0($t0)
# Original instruction: li v28,0
li $t5,0
la $t0,label_64_v28
sw $t5,0($t0)
# Original instruction: li v29,2
li $t5,2
la $t0,label_65_v29
sw $t5,0($t0)
# Original instruction: sub v30,v28,v29
la $t5,label_64_v28
lw $t5,0($t5)
la $t4,label_65_v29
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_68_v30
sw $t3,0($t0)
# Original instruction: li v31,4
li $t5,4
la $t0,label_69_v31
sw $t5,0($t0)
# Original instruction: mul v32,v30,v31
la $t5,label_68_v30
lw $t5,0($t5)
la $t4,label_69_v31
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_72_v32
sw $t3,0($t0)
# Original instruction: add v33,v27,v32
la $t5,label_71_v27
lw $t5,0($t5)
la $t4,label_72_v32
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_74_v33
sw $t3,0($t0)
# Original instruction: lw v34,0(v33)
la $t5,label_74_v33
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_76_v34
sw $t4,0($t0)
# Original instruction: add $a0,v34,$zero
la $t5,label_76_v34
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v35,$zero,$zero
add $t5,$zero,$zero
la $t0,label_77_v35
sw $t5,0($t0)
# Original instruction: addiu v36,$fp,-16
addiu $t5,$fp,-16
la $t0,label_84_v36
sw $t5,0($t0)
# Original instruction: li v37,0
li $t5,0
la $t0,label_81_v37
sw $t5,0($t0)
# Original instruction: li v38,8
li $t5,8
la $t0,label_82_v38
sw $t5,0($t0)
# Original instruction: mul v39,v37,v38
la $t5,label_81_v37
lw $t5,0($t5)
la $t4,label_82_v38
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_85_v39
sw $t3,0($t0)
# Original instruction: add v40,v36,v39
la $t5,label_84_v36
lw $t5,0($t5)
la $t4,label_85_v39
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_96_v40
sw $t3,0($t0)
# Original instruction: li v41,0
li $t5,0
la $t0,label_89_v41
sw $t5,0($t0)
# Original instruction: li v42,1
li $t5,1
la $t0,label_90_v42
sw $t5,0($t0)
# Original instruction: sub v43,v41,v42
la $t5,label_89_v41
lw $t5,0($t5)
la $t4,label_90_v42
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_93_v43
sw $t3,0($t0)
# Original instruction: li v44,4
li $t5,4
la $t0,label_94_v44
sw $t5,0($t0)
# Original instruction: mul v45,v43,v44
la $t5,label_93_v43
lw $t5,0($t5)
la $t4,label_94_v44
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_97_v45
sw $t3,0($t0)
# Original instruction: add v46,v40,v45
la $t5,label_96_v40
lw $t5,0($t5)
la $t4,label_97_v45
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_99_v46
sw $t3,0($t0)
# Original instruction: lw v47,0(v46)
la $t5,label_99_v46
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_101_v47
sw $t4,0($t0)
# Original instruction: add $a0,v47,$zero
la $t5,label_101_v47
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v48,$zero,$zero
add $t5,$zero,$zero
la $t0,label_102_v48
sw $t5,0($t0)
# Original instruction: addiu v49,$fp,-16
addiu $t5,$fp,-16
la $t0,label_109_v49
sw $t5,0($t0)
# Original instruction: li v50,0
li $t5,0
la $t0,label_106_v50
sw $t5,0($t0)
# Original instruction: li v51,8
li $t5,8
la $t0,label_107_v51
sw $t5,0($t0)
# Original instruction: mul v52,v50,v51
la $t5,label_106_v50
lw $t5,0($t5)
la $t4,label_107_v51
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_110_v52
sw $t3,0($t0)
# Original instruction: add v53,v49,v52
la $t5,label_109_v49
lw $t5,0($t5)
la $t4,label_110_v52
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_117_v53
sw $t3,0($t0)
# Original instruction: li v54,0
li $t5,0
la $t0,label_114_v54
sw $t5,0($t0)
# Original instruction: li v55,4
li $t5,4
la $t0,label_115_v55
sw $t5,0($t0)
# Original instruction: mul v56,v54,v55
la $t5,label_114_v54
lw $t5,0($t5)
la $t4,label_115_v55
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_118_v56
sw $t3,0($t0)
# Original instruction: add v57,v53,v56
la $t5,label_117_v53
lw $t5,0($t5)
la $t4,label_118_v56
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_120_v57
sw $t3,0($t0)
# Original instruction: lw v58,0(v57)
la $t5,label_120_v57
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_122_v58
sw $t4,0($t0)
# Original instruction: add $a0,v58,$zero
la $t5,label_122_v58
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v59,$zero,$zero
add $t5,$zero,$zero
la $t0,label_123_v59
sw $t5,0($t0)
# Original instruction: addiu v60,$fp,-16
addiu $t5,$fp,-16
la $t0,label_130_v60
sw $t5,0($t0)
# Original instruction: li v61,0
li $t5,0
la $t0,label_127_v61
sw $t5,0($t0)
# Original instruction: li v62,8
li $t5,8
la $t0,label_128_v62
sw $t5,0($t0)
# Original instruction: mul v63,v61,v62
la $t5,label_127_v61
lw $t5,0($t5)
la $t4,label_128_v62
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_131_v63
sw $t3,0($t0)
# Original instruction: add v64,v60,v63
la $t5,label_130_v60
lw $t5,0($t5)
la $t4,label_131_v63
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_138_v64
sw $t3,0($t0)
# Original instruction: li v65,1
li $t5,1
la $t0,label_135_v65
sw $t5,0($t0)
# Original instruction: li v66,4
li $t5,4
la $t0,label_136_v66
sw $t5,0($t0)
# Original instruction: mul v67,v65,v66
la $t5,label_135_v65
lw $t5,0($t5)
la $t4,label_136_v66
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_139_v67
sw $t3,0($t0)
# Original instruction: add v68,v64,v67
la $t5,label_138_v64
lw $t5,0($t5)
la $t4,label_139_v67
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_141_v68
sw $t3,0($t0)
# Original instruction: lw v69,0(v68)
la $t5,label_141_v68
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_143_v69
sw $t4,0($t0)
# Original instruction: add $a0,v69,$zero
la $t5,label_143_v69
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v70,$zero,$zero
add $t5,$zero,$zero
la $t0,label_144_v70
sw $t5,0($t0)
# Original instruction: addiu v71,$fp,-16
addiu $t5,$fp,-16
la $t0,label_151_v71
sw $t5,0($t0)
# Original instruction: li v72,0
li $t5,0
la $t0,label_148_v72
sw $t5,0($t0)
# Original instruction: li v73,8
li $t5,8
la $t0,label_149_v73
sw $t5,0($t0)
# Original instruction: mul v74,v72,v73
la $t5,label_148_v72
lw $t5,0($t5)
la $t4,label_149_v73
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_152_v74
sw $t3,0($t0)
# Original instruction: add v75,v71,v74
la $t5,label_151_v71
lw $t5,0($t5)
la $t4,label_152_v74
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_159_v75
sw $t3,0($t0)
# Original instruction: li v76,2
li $t5,2
la $t0,label_156_v76
sw $t5,0($t0)
# Original instruction: li v77,4
li $t5,4
la $t0,label_157_v77
sw $t5,0($t0)
# Original instruction: mul v78,v76,v77
la $t5,label_156_v76
lw $t5,0($t5)
la $t4,label_157_v77
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_160_v78
sw $t3,0($t0)
# Original instruction: add v79,v75,v78
la $t5,label_159_v75
lw $t5,0($t5)
la $t4,label_160_v78
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_162_v79
sw $t3,0($t0)
# Original instruction: lw v80,0(v79)
la $t5,label_162_v79
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_164_v80
sw $t4,0($t0)
# Original instruction: add $a0,v80,$zero
la $t5,label_164_v80
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v81,$zero,$zero
add $t5,$zero,$zero
la $t0,label_165_v81
sw $t5,0($t0)
label_7_return_main:
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

