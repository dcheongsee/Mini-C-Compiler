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
str_10:
.asciiz "\n"
# Allocated labels for virtual registers
.align 2
label_175_v76:
.space 4
.align 2
label_90_v35:
.space 4
.align 2
label_209_v97:
.space 4
.align 2
label_101_v34:
.space 4
.align 2
label_152_v68:
.space 4
.align 2
label_142_v63:
.space 4
.align 2
label_179_v82:
.space 4
.align 2
label_55_v20:
.space 4
.align 2
label_158_v71:
.space 4
.align 2
label_121_v44:
.space 4
.align 2
label_81_v32:
.space 4
.align 2
label_42_v14:
.space 4
.align 2
label_146_v65:
.space 4
.align 2
label_196_v90:
.space 4
.align 2
label_188_v86:
.space 4
.align 2
label_45_v15:
.space 4
.align 2
label_115_v50:
.space 4
.align 2
label_136_v61:
.space 4
.align 2
label_131_v58:
.space 4
.align 2
label_49_v17:
.space 4
.align 2
label_77_v30:
.space 4
.align 2
label_26_v5:
.space 4
.align 2
label_176_v80:
.space 4
.align 2
label_46_v16:
.space 4
.align 2
label_58_v18:
.space 4
.align 2
label_160_v72:
.space 4
.align 2
label_91_v38:
.space 4
.align 2
label_151_v67:
.space 4
.align 2
label_128_v57:
.space 4
.align 2
label_173_v79:
.space 4
.align 2
label_102_v43:
.space 4
.align 2
label_127_v56:
.space 4
.align 2
label_191_v88:
.space 4
.align 2
label_186_v85:
.space 4
.align 2
label_59_v22:
.space 4
.align 2
label_20_v2:
.space 4
.align 2
label_67_v25:
.space 4
.align 2
label_111_v48:
.space 4
.align 2
label_32_v8:
.space 4
.align 2
label_182_v83:
.space 4
.align 2
label_203_v94:
.space 4
.align 2
label_35_v9:
.space 4
.align 2
label_74_v29:
.space 4
.align 2
label_17_v0:
.space 4
.align 2
label_116_v51:
.space 4
.align 2
label_61_v12:
.space 4
.align 2
label_71_v27:
.space 4
.align 2
label_172_v78:
.space 4
.align 2
label_110_v45:
.space 4
.align 2
label_64_v24:
.space 4
.align 2
label_206_v96:
.space 4
.align 2
label_157_v70:
.space 4
.align 2
label_107_v46:
.space 4
.align 2
label_96_v41:
.space 4
.align 2
label_204_v95:
.space 4
.align 2
label_119_v52:
.space 4
.align 2
label_183_v84:
.space 4
.align 2
label_99_v42:
.space 4
.align 2
label_190_v87:
.space 4
.align 2
label_164_v74:
.space 4
.align 2
label_169_v77:
.space 4
.align 2
label_135_v60:
.space 4
.align 2
label_68_v26:
.space 4
.align 2
label_108_v47:
.space 4
.align 2
label_62_v23:
.space 4
.align 2
label_185_v81:
.space 4
.align 2
label_201_v93:
.space 4
.align 2
label_38_v11:
.space 4
.align 2
label_48_v13:
.space 4
.align 2
label_138_v59:
.space 4
.align 2
label_122_v53:
.space 4
.align 2
label_78_v31:
.space 4
.align 2
label_141_v54:
.space 4
.align 2
label_29_v6:
.space 4
.align 2
label_88_v37:
.space 4
.align 2
label_52_v19:
.space 4
.align 2
label_95_v40:
.space 4
.align 2
label_163_v73:
.space 4
.align 2
label_197_v91:
.space 4
.align 2
label_148_v66:
.space 4
.align 2
label_166_v75:
.space 4
.align 2
label_98_v39:
.space 4
.align 2
label_36_v10:
.space 4
.align 2
label_24_v4:
.space 4
.align 2
label_139_v62:
.space 4
.align 2
label_130_v55:
.space 4
.align 2
label_213_v99:
.space 4
.align 2
label_200_v92:
.space 4
.align 2
label_18_v1:
.space 4
.align 2
label_210_v98:
.space 4
.align 2
label_214_v100:
.space 4
.align 2
label_193_v89:
.space 4
.align 2
label_23_v3:
.space 4
.align 2
label_30_v7:
.space 4
.align 2
label_82_v33:
.space 4
.align 2
label_118_v49:
.space 4
.align 2
label_72_v28:
.space 4
.align 2
label_145_v64:
.space 4
.align 2
label_154_v69:
.space 4
.align 2
label_56_v21:
.space 4
.align 2
label_87_v36:
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
# Original instruction: addiu $sp,$sp,-28
addiu $sp,$sp,-28
# Original instruction: li v0,0
li $t5,0
la $t0,label_17_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-24
addiu $t5,$fp,-24
la $t0,label_18_v1
sw $t5,0($t0)
# Original instruction: sw v0,0(v1)
la $t5,label_17_v0
lw $t5,0($t5)
la $t4,label_18_v1
lw $t4,0($t4)
sw $t5,0($t4)
label_6_while_start:
# Original instruction: addiu v2,$fp,-24
addiu $t5,$fp,-24
la $t0,label_20_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_20_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_23_v3
sw $t4,0($t0)
# Original instruction: li v4,3
li $t5,3
la $t0,label_24_v4
sw $t5,0($t0)
# Original instruction: slt v5,v3,v4
la $t5,label_23_v3
lw $t5,0($t5)
la $t4,label_24_v4
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_26_v5
sw $t3,0($t0)
# Original instruction: beqz v5,label_7_while_exit
la $t5,label_26_v5
lw $t5,0($t5)
beqz $t5,label_7_while_exit
# Original instruction: li v6,0
li $t5,0
la $t0,label_29_v6
sw $t5,0($t0)
# Original instruction: addiu v7,$fp,-28
addiu $t5,$fp,-28
la $t0,label_30_v7
sw $t5,0($t0)
# Original instruction: sw v6,0(v7)
la $t5,label_29_v6
lw $t5,0($t5)
la $t4,label_30_v7
lw $t4,0($t4)
sw $t5,0($t4)
label_8_while_start:
# Original instruction: addiu v8,$fp,-28
addiu $t5,$fp,-28
la $t0,label_32_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_32_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_35_v9
sw $t4,0($t0)
# Original instruction: li v10,3
li $t5,3
la $t0,label_36_v10
sw $t5,0($t0)
# Original instruction: slt v11,v9,v10
la $t5,label_35_v9
lw $t5,0($t5)
la $t4,label_36_v10
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_38_v11
sw $t3,0($t0)
# Original instruction: beqz v11,label_9_while_exit
la $t5,label_38_v11
lw $t5,0($t5)
beqz $t5,label_9_while_exit
# Original instruction: li v12,46
li $t5,46
la $t0,label_61_v12
sw $t5,0($t0)
# Original instruction: addiu v13,$fp,-17
addiu $t5,$fp,-17
la $t0,label_48_v13
sw $t5,0($t0)
# Original instruction: addiu v14,$fp,-24
addiu $t5,$fp,-24
la $t0,label_42_v14
sw $t5,0($t0)
# Original instruction: lw v15,0(v14)
la $t5,label_42_v14
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_45_v15
sw $t4,0($t0)
# Original instruction: li v16,3
li $t5,3
la $t0,label_46_v16
sw $t5,0($t0)
# Original instruction: mul v17,v15,v16
la $t5,label_45_v15
lw $t5,0($t5)
la $t4,label_46_v16
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_49_v17
sw $t3,0($t0)
# Original instruction: add v18,v13,v17
la $t5,label_48_v13
lw $t5,0($t5)
la $t4,label_49_v17
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_58_v18
sw $t3,0($t0)
# Original instruction: addiu v19,$fp,-28
addiu $t5,$fp,-28
la $t0,label_52_v19
sw $t5,0($t0)
# Original instruction: lw v20,0(v19)
la $t5,label_52_v19
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_55_v20
sw $t4,0($t0)
# Original instruction: li v21,1
li $t5,1
la $t0,label_56_v21
sw $t5,0($t0)
# Original instruction: mul v22,v20,v21
la $t5,label_55_v20
lw $t5,0($t5)
la $t4,label_56_v21
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_59_v22
sw $t3,0($t0)
# Original instruction: add v23,v18,v22
la $t5,label_58_v18
lw $t5,0($t5)
la $t4,label_59_v22
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_62_v23
sw $t3,0($t0)
# Original instruction: sb v12,0(v23)
la $t5,label_61_v12
lw $t5,0($t5)
la $t4,label_62_v23
lw $t4,0($t4)
sb $t5,0($t4)
# Original instruction: addiu v24,$fp,-28
addiu $t5,$fp,-28
la $t0,label_64_v24
sw $t5,0($t0)
# Original instruction: lw v25,0(v24)
la $t5,label_64_v24
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_67_v25
sw $t4,0($t0)
# Original instruction: li v26,1
li $t5,1
la $t0,label_68_v26
sw $t5,0($t0)
# Original instruction: add v27,v25,v26
la $t5,label_67_v25
lw $t5,0($t5)
la $t4,label_68_v26
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_71_v27
sw $t3,0($t0)
# Original instruction: addiu v28,$fp,-28
addiu $t5,$fp,-28
la $t0,label_72_v28
sw $t5,0($t0)
# Original instruction: sw v27,0(v28)
la $t5,label_71_v27
lw $t5,0($t5)
la $t4,label_72_v28
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_8_while_start
j label_8_while_start
label_9_while_exit:
# Original instruction: addiu v29,$fp,-24
addiu $t5,$fp,-24
la $t0,label_74_v29
sw $t5,0($t0)
# Original instruction: lw v30,0(v29)
la $t5,label_74_v29
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_77_v30
sw $t4,0($t0)
# Original instruction: li v31,1
li $t5,1
la $t0,label_78_v31
sw $t5,0($t0)
# Original instruction: add v32,v30,v31
la $t5,label_77_v30
lw $t5,0($t5)
la $t4,label_78_v31
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_81_v32
sw $t3,0($t0)
# Original instruction: addiu v33,$fp,-24
addiu $t5,$fp,-24
la $t0,label_82_v33
sw $t5,0($t0)
# Original instruction: sw v32,0(v33)
la $t5,label_81_v32
lw $t5,0($t5)
la $t4,label_82_v33
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_6_while_start
j label_6_while_start
label_7_while_exit:
# Original instruction: li v34,88
li $t5,88
la $t0,label_101_v34
sw $t5,0($t0)
# Original instruction: addiu v35,$fp,-17
addiu $t5,$fp,-17
la $t0,label_90_v35
sw $t5,0($t0)
# Original instruction: li v36,0
li $t5,0
la $t0,label_87_v36
sw $t5,0($t0)
# Original instruction: li v37,3
li $t5,3
la $t0,label_88_v37
sw $t5,0($t0)
# Original instruction: mul v38,v36,v37
la $t5,label_87_v36
lw $t5,0($t5)
la $t4,label_88_v37
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_91_v38
sw $t3,0($t0)
# Original instruction: add v39,v35,v38
la $t5,label_90_v35
lw $t5,0($t5)
la $t4,label_91_v38
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_98_v39
sw $t3,0($t0)
# Original instruction: li v40,0
li $t5,0
la $t0,label_95_v40
sw $t5,0($t0)
# Original instruction: li v41,1
li $t5,1
la $t0,label_96_v41
sw $t5,0($t0)
# Original instruction: mul v42,v40,v41
la $t5,label_95_v40
lw $t5,0($t5)
la $t4,label_96_v41
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_99_v42
sw $t3,0($t0)
# Original instruction: add v43,v39,v42
la $t5,label_98_v39
lw $t5,0($t5)
la $t4,label_99_v42
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_102_v43
sw $t3,0($t0)
# Original instruction: sb v34,0(v43)
la $t5,label_101_v34
lw $t5,0($t5)
la $t4,label_102_v43
lw $t4,0($t4)
sb $t5,0($t4)
# Original instruction: li v44,79
li $t5,79
la $t0,label_121_v44
sw $t5,0($t0)
# Original instruction: addiu v45,$fp,-17
addiu $t5,$fp,-17
la $t0,label_110_v45
sw $t5,0($t0)
# Original instruction: li v46,1
li $t5,1
la $t0,label_107_v46
sw $t5,0($t0)
# Original instruction: li v47,3
li $t5,3
la $t0,label_108_v47
sw $t5,0($t0)
# Original instruction: mul v48,v46,v47
la $t5,label_107_v46
lw $t5,0($t5)
la $t4,label_108_v47
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_111_v48
sw $t3,0($t0)
# Original instruction: add v49,v45,v48
la $t5,label_110_v45
lw $t5,0($t5)
la $t4,label_111_v48
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_118_v49
sw $t3,0($t0)
# Original instruction: li v50,1
li $t5,1
la $t0,label_115_v50
sw $t5,0($t0)
# Original instruction: li v51,1
li $t5,1
la $t0,label_116_v51
sw $t5,0($t0)
# Original instruction: mul v52,v50,v51
la $t5,label_115_v50
lw $t5,0($t5)
la $t4,label_116_v51
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_119_v52
sw $t3,0($t0)
# Original instruction: add v53,v49,v52
la $t5,label_118_v49
lw $t5,0($t5)
la $t4,label_119_v52
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_122_v53
sw $t3,0($t0)
# Original instruction: sb v44,0(v53)
la $t5,label_121_v44
lw $t5,0($t5)
la $t4,label_122_v53
lw $t4,0($t4)
sb $t5,0($t4)
# Original instruction: li v54,88
li $t5,88
la $t0,label_141_v54
sw $t5,0($t0)
# Original instruction: addiu v55,$fp,-17
addiu $t5,$fp,-17
la $t0,label_130_v55
sw $t5,0($t0)
# Original instruction: li v56,2
li $t5,2
la $t0,label_127_v56
sw $t5,0($t0)
# Original instruction: li v57,3
li $t5,3
la $t0,label_128_v57
sw $t5,0($t0)
# Original instruction: mul v58,v56,v57
la $t5,label_127_v56
lw $t5,0($t5)
la $t4,label_128_v57
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_131_v58
sw $t3,0($t0)
# Original instruction: add v59,v55,v58
la $t5,label_130_v55
lw $t5,0($t5)
la $t4,label_131_v58
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_138_v59
sw $t3,0($t0)
# Original instruction: li v60,2
li $t5,2
la $t0,label_135_v60
sw $t5,0($t0)
# Original instruction: li v61,1
li $t5,1
la $t0,label_136_v61
sw $t5,0($t0)
# Original instruction: mul v62,v60,v61
la $t5,label_135_v60
lw $t5,0($t5)
la $t4,label_136_v61
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_139_v62
sw $t3,0($t0)
# Original instruction: add v63,v59,v62
la $t5,label_138_v59
lw $t5,0($t5)
la $t4,label_139_v62
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_142_v63
sw $t3,0($t0)
# Original instruction: sb v54,0(v63)
la $t5,label_141_v54
lw $t5,0($t5)
la $t4,label_142_v63
lw $t4,0($t4)
sb $t5,0($t4)
# Original instruction: li v64,0
li $t5,0
la $t0,label_145_v64
sw $t5,0($t0)
# Original instruction: addiu v65,$fp,-24
addiu $t5,$fp,-24
la $t0,label_146_v65
sw $t5,0($t0)
# Original instruction: sw v64,0(v65)
la $t5,label_145_v64
lw $t5,0($t5)
la $t4,label_146_v65
lw $t4,0($t4)
sw $t5,0($t4)
label_10_while_start:
# Original instruction: addiu v66,$fp,-24
addiu $t5,$fp,-24
la $t0,label_148_v66
sw $t5,0($t0)
# Original instruction: lw v67,0(v66)
la $t5,label_148_v66
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_151_v67
sw $t4,0($t0)
# Original instruction: li v68,3
li $t5,3
la $t0,label_152_v68
sw $t5,0($t0)
# Original instruction: slt v69,v67,v68
la $t5,label_151_v67
lw $t5,0($t5)
la $t4,label_152_v68
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_154_v69
sw $t3,0($t0)
# Original instruction: beqz v69,label_11_while_exit
la $t5,label_154_v69
lw $t5,0($t5)
beqz $t5,label_11_while_exit
# Original instruction: li v70,0
li $t5,0
la $t0,label_157_v70
sw $t5,0($t0)
# Original instruction: addiu v71,$fp,-28
addiu $t5,$fp,-28
la $t0,label_158_v71
sw $t5,0($t0)
# Original instruction: sw v70,0(v71)
la $t5,label_157_v70
lw $t5,0($t5)
la $t4,label_158_v71
lw $t4,0($t4)
sw $t5,0($t4)
label_12_while_start:
# Original instruction: addiu v72,$fp,-28
addiu $t5,$fp,-28
la $t0,label_160_v72
sw $t5,0($t0)
# Original instruction: lw v73,0(v72)
la $t5,label_160_v72
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_163_v73
sw $t4,0($t0)
# Original instruction: li v74,3
li $t5,3
la $t0,label_164_v74
sw $t5,0($t0)
# Original instruction: slt v75,v73,v74
la $t5,label_163_v73
lw $t5,0($t5)
la $t4,label_164_v74
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_166_v75
sw $t3,0($t0)
# Original instruction: beqz v75,label_13_while_exit
la $t5,label_166_v75
lw $t5,0($t5)
beqz $t5,label_13_while_exit
# Original instruction: addiu v76,$fp,-17
addiu $t5,$fp,-17
la $t0,label_175_v76
sw $t5,0($t0)
# Original instruction: addiu v77,$fp,-24
addiu $t5,$fp,-24
la $t0,label_169_v77
sw $t5,0($t0)
# Original instruction: lw v78,0(v77)
la $t5,label_169_v77
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_172_v78
sw $t4,0($t0)
# Original instruction: li v79,3
li $t5,3
la $t0,label_173_v79
sw $t5,0($t0)
# Original instruction: mul v80,v78,v79
la $t5,label_172_v78
lw $t5,0($t5)
la $t4,label_173_v79
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_176_v80
sw $t3,0($t0)
# Original instruction: add v81,v76,v80
la $t5,label_175_v76
lw $t5,0($t5)
la $t4,label_176_v80
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_185_v81
sw $t3,0($t0)
# Original instruction: addiu v82,$fp,-28
addiu $t5,$fp,-28
la $t0,label_179_v82
sw $t5,0($t0)
# Original instruction: lw v83,0(v82)
la $t5,label_179_v82
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_182_v83
sw $t4,0($t0)
# Original instruction: li v84,1
li $t5,1
la $t0,label_183_v84
sw $t5,0($t0)
# Original instruction: mul v85,v83,v84
la $t5,label_182_v83
lw $t5,0($t5)
la $t4,label_183_v84
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_186_v85
sw $t3,0($t0)
# Original instruction: add v86,v81,v85
la $t5,label_185_v81
lw $t5,0($t5)
la $t4,label_186_v85
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_188_v86
sw $t3,0($t0)
# Original instruction: lb v87,0(v86)
la $t5,label_188_v86
lw $t5,0($t5)
lb $t4,0($t5)
la $t0,label_190_v87
sw $t4,0($t0)
# Original instruction: add $a0,v87,$zero
la $t5,label_190_v87
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,11
li $v0,11
# Original instruction: syscall
syscall
# Original instruction: add v88,$zero,$zero
add $t5,$zero,$zero
la $t0,label_191_v88
sw $t5,0($t0)
# Original instruction: addiu v89,$fp,-28
addiu $t5,$fp,-28
la $t0,label_193_v89
sw $t5,0($t0)
# Original instruction: lw v90,0(v89)
la $t5,label_193_v89
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_196_v90
sw $t4,0($t0)
# Original instruction: li v91,1
li $t5,1
la $t0,label_197_v91
sw $t5,0($t0)
# Original instruction: add v92,v90,v91
la $t5,label_196_v90
lw $t5,0($t5)
la $t4,label_197_v91
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_200_v92
sw $t3,0($t0)
# Original instruction: addiu v93,$fp,-28
addiu $t5,$fp,-28
la $t0,label_201_v93
sw $t5,0($t0)
# Original instruction: sw v92,0(v93)
la $t5,label_200_v92
lw $t5,0($t5)
la $t4,label_201_v93
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_12_while_start
j label_12_while_start
label_13_while_exit:
# Original instruction: la v94,str_10
la $t5,str_10
la $t0,label_203_v94
sw $t5,0($t0)
# Original instruction: add $a0,v94,$zero
la $t5,label_203_v94
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v95,$zero,$zero
add $t5,$zero,$zero
la $t0,label_204_v95
sw $t5,0($t0)
# Original instruction: addiu v96,$fp,-24
addiu $t5,$fp,-24
la $t0,label_206_v96
sw $t5,0($t0)
# Original instruction: lw v97,0(v96)
la $t5,label_206_v96
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_209_v97
sw $t4,0($t0)
# Original instruction: li v98,1
li $t5,1
la $t0,label_210_v98
sw $t5,0($t0)
# Original instruction: add v99,v97,v98
la $t5,label_209_v97
lw $t5,0($t5)
la $t4,label_210_v98
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_213_v99
sw $t3,0($t0)
# Original instruction: addiu v100,$fp,-24
addiu $t5,$fp,-24
la $t0,label_214_v100
sw $t5,0($t0)
# Original instruction: sw v99,0(v100)
la $t5,label_213_v99
lw $t5,0($t5)
la $t4,label_214_v100
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_10_while_start
j label_10_while_start
label_11_while_exit:
label_5_return_main:
# Original instruction: addiu $sp,$sp,28
addiu $sp,$sp,28
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

