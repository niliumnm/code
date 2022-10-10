/*******************************************************************************
 * ͼ��ѧ��ѧ���򣺱���ü�
 * ��Ȩ����������������ʹ�á����ƻ򴫲������룬���뱣��ԭ��������
 * ���ߣ��ɶ�����ѧ��Ϣ����ѧԺ-�˷�  2010.8
*******************************************************************************/
 
#include <gl/glut.h>
#include <stdio.h>
#include "tools.h"
#include "gpoint2.h"
#include "grect2.h"

// ��¼ֱ�ߵ������˵� 
GPoint2d gPt0, gPt1;

// �ü��Ժ�Ķ˵� 
GPoint2d gCPt0, gCPt1;

// �ü����ھ���
GRect2d gWinRect;

// �Ƿ���Ҫ���� 
bool gIsPaint = false;

// �������Ƿ��� 
bool gIsLBtnDown = false;

// �����¼����� 
void displayEvent()
{
    glClearColor(1, 1, 1, 0);
    glClear(GL_COLOR_BUFFER_BIT);
    
    glColor3f(0, 0, 1);
	gltRect2d(gWinRect.x0(), gWinRect.y0(), gWinRect.x1(), gWinRect.y1());
    
    if(gIsPaint)
    {
        // ����ֱ�� 
        glColor3f(0, 0, 0);
        gltLine2d(gPt0.x(), gPt0.y(), gPt1.x(), gPt1.y());
        
        // ��������ɫʮ�� 
        glColor3f(1, 0, 0);
        gltLine2d(gPt0.x()-8, gPt0.y(), gPt0.x()+8, gPt0.y());
        gltLine2d(gPt0.x(), gPt0.y()-8, gPt0.x(), gPt0.y()+8);
        
        // ����β�㷽�� 
        glColor3f(0, 0, 1);
        gltRect2d(gPt1.x()-5, gPt1.y()-5, gPt1.x()+5, gPt1.y()+5);
        
        if(!gIsLBtnDown)
        {
            glColor3f(1, 0, 0);
            glLineWidth(2);
            gltLine2d(gCPt0.x(), gCPt0.y(), gCPt1.x(), gCPt1.y());
            glLineWidth(1);
        }
        
        // ��ע���� 
        char text[32];
        sprintf(text, "(%d, %d)", (int)gPt0.x(), (int)gPt0.y());
        glColor3f(1, 0, 0);
        gltRasterText(gPt0.x()+10, gPt0.y()-5, text);
        
        sprintf(text, "(%d, %d)", (int)gPt1.x(), (int)gPt1.y());
        glColor3f(0, 0, 1);
        gltRasterText(gPt1.x()+10, gPt1.y()-5, text);
    }

    glFlush();
    glutSwapBuffers();  // �������� 
}

// ����ߴ�仯�¼� 
void resizeEvent(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    gluOrtho2D(0, w, 0, h);
    glMatrixMode(GL_MODELVIEW);
    glutPostRedisplay();
}       

void mouseMoveEvent(int x, int y)
{
    int h = glutGet(GLUT_WINDOW_HEIGHT);
    y = h - y;
    if(gIsLBtnDown)
    {
        gPt1.set(x, y);
        glutPostRedisplay();
    }
}        

// ����¼����� 
void mouseEvent(int button, int state, int x, int y)
{
    int h = glutGet(GLUT_WINDOW_HEIGHT);
    y = h - y;
    if(button == GLUT_LEFT_BUTTON)
    {
        if(state == GLUT_DOWN)
        {
            gPt0.set(x, y);
            gPt1 = gPt0;
            gIsLBtnDown = true;
            gIsPaint = true;
        }
        else if(state == GLUT_UP)
        {    
            gPt1.set(x, y);
            
            gCPt0 = gPt0;       gCPt1 = gPt1;
            if(!gltLineClip2d(gCPt0, gCPt1, gWinRect))
            {
                gCPt0.set(-1, -1);
                gCPt1.set(-1, -1);
            }
            gIsLBtnDown = false;
        }            
        glutPostRedisplay();
    }
}                   

// openGL��ʼ������ 
void glInit()
{
	gWinRect.set(50, 60, 350, 260);
} 

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 300);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Code Clip");
	glInit();
	
	glutReshapeFunc(resizeEvent);
	glutDisplayFunc(displayEvent);
	
	glutMouseFunc(mouseEvent);
	glutMotionFunc(mouseMoveEvent);
	
	glutMainLoop();
	
	return 0;
}
