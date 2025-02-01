LATEX = latexmk -pdf
BIBTEX  = bibtex
MAIN_SOURCE = rules
VISUALIZE = evince

SOURCES = $(wildcard *.tex)  $(wildcard *.cls) $(wildcard *.bib) $(wildcard ../*.tex)

RM = rm -f

$(MAIN_SOURCE).pdf: $(SOURCES) $(FIGURES) $(PLOTS)
	$(LATEX) $(MAIN_SOURCE)
	$(VISUALIZE) $(MAIN_SOURCE).pdf &

.PHONY: clean

clean:
	$(RM) *.aux *.dvi *.log *.lof *.lot *.blg *.d *.bbl *.out *.toc *.snm *.nav *.vrb *.pdf

cleanall:
	$(RM) *.aux *.dvi *.log *.lof *.lot *.blg *.d *.bbl *.out *.toc *.snm *.nav *.vrb *.pdf

plots/%.pdf: plots/%.R
	R --no-save < $<

#figures/%.svg: figures/%.dia
#	dia --export=$@ $<

figures/%.pdf: figures/%.svg
	inkscape --export-pdf=$@ $<

#plots/first_results_nv.pdf: plots/first_results_nv.R plots/first_results.csv

#plots/first_results_amd.pdf: plots/first_results_amd.R plots/first_results.csv


