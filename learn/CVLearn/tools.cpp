#include <string.h>
#include "tools.h"

template <class T>
void swap(T &a, T &b)
{
	T t = a;
	a = b;
	b = t;
}

int gltRound(double x)
{
	return x > 0 ? (int)(x+0.5) : (int)(x-0.5);
}

int gltTrunc(double x)
{
	return (int)x;
}

void gltRasterText(double x, double y, const char *text, void *font)
{
    if(text == NULL) return ;
    
    glRasterPos2d(x, y);
    for(int i=0; text[i] != '\0'; i++)
    {
        glutBitmapCharacter(font, text[i]);
    } 
}

void gltLine2i(int x0, int y0, int x1, int y1)
{
    glBegin(GL_LINES);
	glVertex2i(x0, y0);
	glVertex2i(x1, y1);
    glEnd();
}

void gltRect2i(int x0, int y0, int x1, int y1)
{
    glBegin(GL_LINES);
	glVertex2i(x0, y0);
	glVertex2i(x1, y0);
	
	glVertex2i(x1, y0);
	glVertex2i(x1, y1);
	
	glVertex2i(x1, y1);
	glVertex2i(x0, y1);
	
	glVertex2i(x0, y1);
	glVertex2i(x0, y0);
    glEnd();
}

void gltLine2d(double x0, double y0, double x1, double y1)
{
    glBegin(GL_LINES);
        glVertex2d(x0, y0);
        glVertex2d(x1, y1);
    glEnd();
}

void gltRect2d(double x0, double y0, double x1, double y1)
{
    glBegin(GL_LINES);
        glVertex2d(x0, y0);
        glVertex2d(x1, y0);
        
        glVertex2d(x1, y0);
        glVertex2d(x1, y1);
        
        glVertex2d(x1, y1);
        glVertex2d(x0, y1);
        
        glVertex2d(x0, y1);
        glVertex2d(x0, y0);
    glEnd();
}

bool gltPtInPolygon(int x, int y, const GPoint2i *points, int n)
{
	return false;
}

struct EdgeNode     // �߽ڵ� 
{
	int yMax;
	double x, invk;
	EdgeNode *next;
	
	EdgeNode()
	{
		yMax = 0;
		x = 0;
		invk = 0;
		next = 0;
	}
};

void gltFillPolygon(const GPoint2i* points, int n)
{
    if (n < 3) return;

    int i, index;
    int min, max;
    EdgeNode** edgeBucket;       // �߱�Ͱ  
    EdgeNode* p, * q, * pEdge, * aet;
    GPoint2i pt0, pt1, pt;

    // Ѱ�Ҷ���� Y ����ɨ�跶Χ 
    min = max = points[0].y();

    for (i = 1; i < n; i++)
    {
        pt = points[i];
        if (min > (int)pt.y()) min = (int)pt.y();
        if (max < (int)pt.y()) max = (int)pt.y();
    }

    // �����߱�Ͱ  
    edgeBucket = new EdgeNode * [max - min + 1];
    memset(edgeBucket, 0, sizeof(EdgeNode*) * (max - min + 1));
    // ����߽ڵ㵽Ͱ�� 
    for (i = 0; i < n; i++)
    {
        pt0 = points[i];
        pt1 = points[(i + 1) % n];
        // ����ˮƽ��  
        if (pt0.y() == pt1.y()) continue;
        pEdge = new EdgeNode;
        if (pt0.y() > pt1.y()) // ��֤ pt0 Ϊ y ֵ��С��  
        {
            pt = pt0;
            pt0 = pt1;
            pt1 = pt;
        }
        pEdge->yMax = pt1.y();
        pEdge->x = pt0.x();
        pEdge->invk = (double)(pt1.x() - pt0.x()) / (pt1.y() - pt0.y());
        // �߱����������ͷ��  
        index = pt0.y() - min;
        pEdge->next = edgeBucket[index];
        edgeBucket[index] = pEdge;
    }

    // ɨ����� 
    aet = new EdgeNode;
    for (i = min; i <= max; i++)
    {
        // (1)ɾ�� y=ymax �ı��� 
        p = aet;
        while (p->next != 0)
        {
            pEdge = p->next;
            if (pEdge->yMax == i)
            {
                p->next = pEdge->next;
                delete pEdge;
            }
            else p = p->next;
        }
        // (2)���߱�Ͱ�еı߱��������߱�  
        index = i - min;
        p = edgeBucket[index];
        while (p != 0)
        {
            pEdge = p;
            p = p->next;
            q = aet;
            while (q->next != 0)
            {
                if ((int)q->next->x > (int)pEdge->x) break;
                else if ((int)q->next->x == (int)pEdge->x)
                {
                    if (q->next->invk > pEdge->invk) break;
                }
                q = q->next;
            }
            pEdge->next = q->next;
            q->next = pEdge;
        }
        // (3)������ 
        p = aet->next;
        while (p != 0)
        {
            gltLine2i(p->x, i, p->next->x, i);
            p = p->next->next;
        }
        // (4)�޸� AET ����, ���� x ֵ 
        p = aet->next;
        while (p != 0)
        {
            p->x += p->invk;
            p = p->next;
        }
    }
    // �ͷ�ʣ���ڴ� 
    delete aet;
    delete[]edgeBucket;
}