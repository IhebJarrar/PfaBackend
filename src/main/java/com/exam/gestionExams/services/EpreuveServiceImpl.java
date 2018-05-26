package com.exam.gestionExams.services;

import java.io.FileOutputStream;
import java.util.List;

import com.exam.gestionExams.model.Surveillant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.repository.EpreuveRepository;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Service
public class EpreuveServiceImpl implements EpreuveService {

	@Autowired
    EpreuveRepository epreuveRepository;

	
	public List<Epreuves> getAllEpreuves() {
		return epreuveRepository.findAll();
	}

	@Override
	public Epreuves getEpreuve(Long id) {
		return epreuveRepository.findOne(id);
	}

	@Override
	public void epreuveToPdf(Epreuves e) {
		String result = "/home/iheb/Bureau/pdf/epreuve"+e.getId()+".pdf";
		try {

			// step 1
			Rectangle r=new Rectangle(880, 490);
			Document document = new Document(r);
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(result));
			// step 3
			document.open();

			// header
			String s="---------------------------------------------------";
			Paragraph p= new Paragraph(s+s+s+"Enveloppe"+s, new Font(FontFamily.HELVETICA, 11));
			p.setAlignment(Element.ALIGN_TOP);
			Paragraph p2= new Paragraph("Ecole Pluridisciplinaire Internationale\n", new Font(FontFamily.HELVETICA, 11));
			document.add(p);
			document.add(p2);

			// salle rectangle
			PdfPTable table = new PdfPTable(1);
			String nomSalle="";
			if(e.getLocal()!=null) nomSalle=e.getLocal().getNom()+"E"+e.getLocal().getEtage();
			Paragraph salle= new Paragraph("Salle : "+nomSalle+"\n", new Font(FontFamily.TIMES_ROMAN, 22));
			PdfPCell cell = new PdfPCell(new Phrase(salle));
			cell.setRowspan(400);
			table.addCell(cell);
			table.setWidthPercentage(20);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			document.add(table);
			document.add(new Paragraph("\n"));

			//matrice du centre
			PdfPTable donnéeTable = new PdfPTable(2);
			donnéeTable.setWidthPercentage(100);

			//donnée a gauche
			PdfPCell  cellGauche=new PdfPCell ();
			cellGauche.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cellGauche.setPadding(0);
			cellGauche.setBorderWidthLeft(10);
			cellGauche.setBorder(PdfPCell.NO_BORDER);
			//le tableau du gauche
			PdfPTable rectDonnée = new PdfPTable(1);
			Paragraph donnée= new Paragraph("Date : "+e.getCreneau().getDate()+"\nSeance : "+e.getCreneau().getSeance().getHeureDebut()+"\nDurée(h) : "+e.getDuree()+"\nParcours : "+e.getGroupe().getClasse().getNivSpecialite().getSpecialite().getNom()
					+ "\nNiveau : "+e.getGroupe().getClasse().getNivSpecialite().getNiveau().getNiveau()+"\nN° EPV : "+e.getId()+"\nNom Epreuve : "+e.getNom()+"\n ", new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));
			PdfPCell cellrectDonnée = new PdfPCell(new Phrase(donnée));
			rectDonnée.addCell(cellrectDonnée);
			//le reste du gauche
			Paragraph groupe=new Paragraph("Groupe : "+e.getGroupe().getNom()+"\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));
			Paragraph details=new Paragraph("Détails de l'épreuve : \n\n\n\n\n\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.ITALIC));
			//finaliser la gauche
			cellGauche.addElement(rectDonnée);
			cellGauche.addElement(groupe);
			cellGauche.addElement(details);
			donnéeTable.addCell(cellGauche);

			//partie droite
			PdfPCell  cellDroite=new PdfPCell ();
			cellDroite.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cellDroite.setPaddingLeft(20);
			cellDroite.setBorder(PdfPCell.NO_BORDER);
			cellDroite.setRowspan(800);

			//donnée simple
			Paragraph dataDroite =new Paragraph("Nb Etudiant :"+e.getGroupe().getCapacite()+"           Nb Présents : ......\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));
			Paragraph dataDroite3 =new Paragraph("Absent(s) : \n\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));
			//cas des surveillants
			List<Surveillant>surv=e.getSurveillants();
			String survResponsable="";
			StringBuilder survRestant=new StringBuilder("\n");
			if(surv!=null)
			{
				switch (surv.size()) {
					case 1:
					{
						survResponsable=surv.get(0).getNom();
						survRestant.append("\n\n");
						break;
					}
					case 2:
					{
						survResponsable=surv.get(0).getNom();
						survRestant.append(surv.get(1).getNom()+"\n");
						break;
					}

					case 3:
					{
						survResponsable=surv.get(0).getNom();
						survRestant.append(surv.get(1).getNom()+"\n");
						survRestant.append(surv.get(2).getNom()+"\n");
						break;
					}
					default:
						break;

				}
			}

			Paragraph dataDroite2 =new Paragraph("Enseignant(s) Responsable(s) : "+survResponsable+"\n Surveillant(s) : "+survRestant,new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));

			//tableau absent
			PdfPTable tableAbsent = new PdfPTable(1);
			PdfPCell cellabsent = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n"));
			tableAbsent.addCell(cellabsent);

			//finaliser la droite
			cellDroite.addElement(dataDroite);
			cellDroite.addElement(dataDroite2);
			cellDroite.addElement(dataDroite3);
			cellDroite.addElement(tableAbsent);
			donnéeTable.addCell(cellDroite);
			//finaliser matrice centrale
			document.add(donnéeTable);


			// footer
			String s1="------------------------------------------------------";
			Paragraph p1= new Paragraph(s1+s1+s1+s1+" Transmis Le : .................................... "
					+ "					Signature : ............................................."	, new Font(FontFamily.HELVETICA, 11));
			p1.setAlignment(Element.ALIGN_BOTTOM);
			document.add(p1);

			//final step
			document.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void ListepreuvesToPdf(List<Epreuves> liste) {
		if(liste!=null)
		{
			for(Epreuves e:liste)
			{
				epreuveToPdf(e);
			}
		}
	}
}
